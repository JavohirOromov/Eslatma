package com.example.eslatma.screen.note
import BottomSheet
import NoteViewModelFactory
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eslatma.R
import com.example.eslatma.databinding.FragmentNoteBinding

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 02/04/25
 * Javohir's MacBook Air
 */
class NoteFragment : Fragment(R.layout.fragment_note) {
    private val binding: FragmentNoteBinding by viewBinding(FragmentNoteBinding::bind)
    private lateinit var viewModel: NoteViewModel
    private var globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null
    private lateinit var formatList: ArrayList<ImageButton>
    private val args: NoteFragmentArgs by navArgs()
    private val bottom by lazy { BottomSheet(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            NoteViewModelFactory(binding.noteContent)
        )[NoteViewModelImpl::class.java]
        viewModel.setId(args.id)
        viewModel.openMainLiveDate.observe(requireActivity(), openMainObserver)
        viewModel.visibilitySaveBtn.observe(viewLifecycleOwner,visibilitySaveBtnObserver)
        viewModel.visibilityDeleteBtn.observe(viewLifecycleOwner,visibilityDeleteBtnObserver)
        viewModel.visibilityUpdateBtn.observe(viewLifecycleOwner,visibilityUpdateObserver)
        viewModel.noteTitleSetTextLiveData.observe(viewLifecycleOwner,noteTitleObserver)
        viewModel.noteContentSetTextLiveData.observe(viewLifecycleOwner,noteContentObserver)
        viewModel.setBackgroundLiveData.observe(viewLifecycleOwner,setBackgroundObserver)
        viewModel.clearOldStateLiveData.observe(viewLifecycleOwner,clearOldStateObserver)
        viewModel.showBottomSheetLiveData.observe(viewLifecycleOwner,showBottomSheetObserver)
        viewModel.setColorLiveData.observe(viewLifecycleOwner,setColorObserver)
        checkKeyboard()
        loadData()
        addClickEvents()
    }

    private fun loadData(){
        formatList = ArrayList()
        for (i in 0 until binding.liner.childCount){
            formatList.add(binding.liner.getChildAt(i) as ImageButton)
        }
        binding.noteContent.apply {
            setEditorHeight(200)
            setEditorFontSize(16)
            setEditorFontColor(Color.BLACK)
            setPadding(10, 10, 10, 10)
            setPlaceholder("Eslatma matnini kiriting...")
        }
    }
    private fun addClickEvents() {
        binding.back.setOnClickListener {
            viewModel.openMainScreen()
        }
        binding.save.setOnClickListener {
            viewModel.saveNote(binding.noteTitle.text.toString())
        }
        binding.delete.setOnClickListener {
            viewModel.deleteNote(args.id)
        }
        binding.update.setOnClickListener {
            viewModel.updateNote(args.id,binding.noteTitle.text.toString())
        }
        for (i in 0 until binding.liner.childCount){
            binding.liner.getChildAt(i).setOnClickListener {
              viewModel.formatBtn(i)
            }
        }
        binding.paint.setOnClickListener {
            viewModel.showBottomSheet()
        }
        bottom.setColorClickListener {
            viewModel.setColor(it)
        }
    }

    private val openMainObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
    private val visibilitySaveBtnObserver = Observer<Boolean>{
        if (it){
            binding.save.visibility = View.VISIBLE
        }else{
            binding.save.visibility = View.GONE
        }
    }
    private val visibilityDeleteBtnObserver = Observer<Boolean>{
        if (it){
            binding.delete.visibility = View.VISIBLE
        }else{
            binding.delete.visibility = View.GONE
        }
    }
    private val visibilityUpdateObserver = Observer<Boolean>{
        if (it){
            binding.update.visibility = View.VISIBLE
        }else{
            binding.update.visibility = View.GONE
        }
    }

    private val noteTitleObserver = Observer<String>{
        binding.noteTitle.setText(it)
    }
    private val noteContentObserver = Observer<String> {
        binding.noteContent.html = it
    }
    private val setBackgroundObserver = Observer<Int>{
        for (i in 0 until binding.liner.childCount){
            if (it == i){
                binding.liner.getChildAt(i).setBackgroundResource(R.drawable.bg_3)
                return@Observer
            }
        }
    }
    private val clearOldStateObserver = Observer<Unit>{
        for (i in 0 until binding.liner.childCount){
            binding.liner.getChildAt(i).setBackgroundResource(R.drawable.bg_2)
        }
    }
    private val showBottomSheetObserver = Observer<Unit>{
        bottom.show()
    }
    private val setColorObserver = Observer<Int>{
        Log.d("QQQ","$it")
        binding.root.setBackgroundResource(it)
    }
    private fun checkKeyboard() {
        binding.noteTitle.setOnFocusChangeListener { _, hasFocus ->
            binding.liner.visibility = if (hasFocus) View.GONE else View.VISIBLE
        }


        globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            if (!isAdded) return@OnGlobalLayoutListener

            val rect = Rect()
            binding.root.getWindowVisibleDisplayFrame(rect)

            val screenHeight = binding.root.rootView.height
            val navigationBarHeight = getNavigationBarHeight(requireContext())

            val keypadHeight = screenHeight - rect.bottom - navigationBarHeight

            if (keypadHeight > screenHeight * 0.15) {
                if (!binding.noteTitle.hasFocus()) {
                    binding.liner.visibility = View.VISIBLE
                }

                binding.liner.setPadding(
                    binding.liner.paddingLeft,
                    binding.liner.paddingTop,
                    binding.liner.paddingRight,
                    keypadHeight
                )

            } else {
                binding.liner.visibility = View.GONE
                binding.liner.setPadding(
                    binding.liner.paddingLeft,
                    binding.liner.paddingTop,
                    binding.liner.paddingRight,
                    0
                )
            }
        }
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    private fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }
    override fun onDestroyView() {
        globalLayoutListener?.let {
            binding.root.viewTreeObserver.removeOnGlobalLayoutListener(it)
        }
        globalLayoutListener = null
        super.onDestroyView()
    }
}