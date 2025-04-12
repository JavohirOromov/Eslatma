package com.example.eslatma.screen.main
import MainAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eslatma.R
import com.example.eslatma.databinding.FragmentMainBinding
import com.example.eslatma.model.room.entity.NoteEntity

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
        viewModel.allNotes()
        addClickEvents()
        initAdapter()
    }
    private fun initAdapter(){
        binding.listItem.adapter = adapter
        binding.listItem.layoutManager = GridLayoutManager(requireContext(),2)
    }
    private fun addClickEvents(){
        binding.addNote.setOnClickListener {
            viewModel.openNoteScreen()
        }
        adapter.setItemClickListener {
            viewModel.updateLiveData(it)
        }
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
}