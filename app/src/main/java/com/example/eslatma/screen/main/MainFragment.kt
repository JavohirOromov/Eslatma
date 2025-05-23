package com.example.eslatma.screen.main
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eslatma.R
import com.example.eslatma.adapter.MainAdapter
import com.example.eslatma.databinding.FragmentMainBinding
import com.example.eslatma.model.room.entity.NoteEntity
import java.util.Locale

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 02/04/25
 * Javohir's MacBook Air
 */
class MainFragment: Fragment(R.layout.fragment_main){
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter by lazy { MainAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openNoteLiveData.observe(this,openNoteScreenObserver)
        viewModel.updateNoteLiveData.observe(this,updateNoteObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allNotesLiveData.observe(viewLifecycleOwner,allNotesObserver)
        viewModel.searchQueryObserver.observe(viewLifecycleOwner,searchQueryObserver)
        viewModel.setInputSearchQuery.observe(viewLifecycleOwner,setInputSearchQueryObserver)
        addClickEvents()
        initAdapter()
    }
    private fun initAdapter(){
        binding.listItem.adapter = adapter
        binding.listItem.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    }
    private fun addClickEvents(){
        binding.addNote.setOnClickListener {
            viewModel.openNoteScreen()
        }
        adapter.setItemClickListener {
            viewModel.updateLiveData(it)
        }
        binding.seach.addTextChangedListener(object: MyTextWatcher{
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                  viewModel.searchQuery(it.toString())
                }
            }
        })
        binding.mic.setOnClickListener {
            startVoiceSearch()
        }
    }
    private fun startVoiceSearch(){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Gapiring...")
        }
        try {
            startActivityForResult(intent, REQUEST_CODE_VOICE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Ovozli qidiruv qo‘llab-quvvatlanmaydi!", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_VOICE && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!result.isNullOrEmpty()) {
                viewModel.setVoiceResult(result[0])
            }
        }
    }
    companion object {
        private const val REQUEST_CODE_VOICE = 100
    }
    private val allNotesObserver = Observer<List<NoteEntity>>{ entityList ->
        adapter.submitList(entityList)
    }
    private val openNoteScreenObserver = Observer<Unit>{
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToNoteFragment())
    }
    private val updateNoteObserver = Observer<Int>{ id ->
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToNoteFragment(id))
    }
    private val searchQueryObserver = Observer<List<NoteEntity>>{
        adapter.submitList(it)
    }
    private val setInputSearchQueryObserver = Observer<String>{
        binding.seach.setText(it)
    }
}

interface MyTextWatcher: TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}