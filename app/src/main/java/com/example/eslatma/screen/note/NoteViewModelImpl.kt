package com.example.eslatma.screen.note
import AppRepositoryImpl
import RichEditorHelp
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eslatma.R
import com.example.eslatma.model.room.entity.NoteEntity
import com.example.eslatma.repository.repository.AppRepository
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 02/04/25
 * Javohir's MacBook Air
 */
class NoteViewModelImpl(private val editorHelper: RichEditorHelp): NoteViewModel, ViewModel() {
    private val repository: AppRepository = AppRepositoryImpl.getInstance()
    override val openMainLiveDate = MutableLiveData<Unit>()
    override val visibilityDeleteBtn = MutableLiveData<Boolean>()
    override val visibilityUpdateBtn = MutableLiveData<Boolean>()
    override val visibilitySaveBtn = MutableLiveData<Boolean>()
    override val noteTitleSetTextLiveData = MutableLiveData<String>()
    override val noteContentSetTextLiveData = MutableLiveData<String>()
    override val setBackgroundLiveData = MutableLiveData<Int>()
    override val clearOldStateLiveData = MutableLiveData<Unit>()
    override val showBottomSheetLiveData = MutableLiveData<Unit>()
    override val setColorLiveData = MutableLiveData<Int>()


    override fun openMainScreen() {
        openMainLiveDate.value = Unit
    }

    override fun saveNote(title: String) {
        val content = editorHelper.getHtml()
        val data = NoteEntity(0,title,content,"","", R.drawable.bg_3)
        repository.addNote(data)
        openMainLiveDate.value = Unit
    }

    override fun setId(id: Int) {
        if (id != -1){
            visibilityDeleteBtn.value = true
            visibilityUpdateBtn.value = true
            visibilitySaveBtn.value = false
            noteTitleSetTextLiveData.value = repository.getNoteById(id).title
            noteContentSetTextLiveData.value = repository.getNoteById(id).content
        }else{
            visibilityUpdateBtn.value = false
            visibilityUpdateBtn.value = false
            visibilitySaveBtn.value = true
        }
    }

    override fun updateNote(id: Int, title: String) {
        val content = editorHelper.getHtml()
        val data = NoteEntity(id,title,content,"","", R.drawable.bg_3)
        repository.updateNote(data)
        openMainLiveDate.value = Unit
    }

    override fun showBottomSheet() {
        showBottomSheetLiveData.value = Unit
    }

    override fun deleteNote(id: Int) {
        val data = NoteEntity(id,"","","","", R.drawable.bg_3)
        repository.deleteNote(data)
        openMainLiveDate.value = Unit
    }

    override fun setColor(id: Int) {
        Log.d("WWW","$id")
        when(id){
            0 -> setColorLiveData.value = R.drawable.bg_4
            1 -> setColorLiveData.value = R.drawable.bg_5
            2 -> setColorLiveData.value = R.drawable.bg_6
            3 -> setColorLiveData.value = R.drawable.bg_7
            4 -> setColorLiveData.value = R.drawable.bg_8
            5 -> setColorLiveData.value = R.drawable.bg_9
            6 -> setColorLiveData.value = R.drawable.bg_10
            7 -> setColorLiveData.value = R.drawable.bg_11
            8 -> setColorLiveData.value = R.drawable.bg_12
            9 -> setColorLiveData.value = R.drawable.bg_13
            10 -> setColorLiveData.value = R.drawable.bg_14
            11 -> setColorLiveData.value = R.drawable.bg_15
            12 -> setColorLiveData.value = R.drawable.bg_16
        }
    }

    override fun setBold() {
        editorHelper.setBold()
    }

    override fun setItalic() {
        editorHelper.setItalic()
    }

    override fun setUnderline() {
        editorHelper.setUnderline()
    }

    override fun setBulletList() {
        editorHelper.setBulletList()
    }

    override fun setNumberedList() {
        editorHelper.setNumberedList()
    }

    override fun setCheckbox() {
        editorHelper.setCheckBox()
    }

    override fun formatBtn(index: Int) {
        clearOldStateLiveData.value = Unit
       // editorHelper.clearFormats()
        when(index){
            0 ->{
                setBold()
            }
            1 ->{
                setItalic()
            }
            2 ->{
                setUnderline()
            }
            3 ->{
                setBulletList()
            }
            4 ->{
                setNumberedList()
            }
            5 ->{
                setCheckbox()
            }
        }
        setBackgroundLiveData.value = index
    }
}