package com.example.eslatma.screen.note
import AppRepositoryImpl
import RichEditorHelp
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eslatma.R
import com.example.eslatma.model.room.entity.NoteEntity
import com.example.eslatma.repository.repository.AppRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 02/04/25
 * Javohir's MacBook Air
 */
class NoteViewModelImpl(private val editorHelper: RichEditorHelp): NoteViewModel, ViewModel() {
    private var data: String = ""
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
    override val setColorLiveData = MutableLiveData<Pair<Int,Int>>()
    override val setDataLiveData = MutableLiveData<String>()
    override val showToast = MutableLiveData<String>()



    override fun openMainScreen() {
        openMainLiveDate.value = Unit
    }

    override fun saveNote(title: String, id: Int) {
        val content = editorHelper.getHtml()?: ""
        if (title.isEmpty()){
            showToast.value = "Sarlavhani matnini kriting"
            return
        }
        if (content.isEmpty()){
            showToast.value = "Eslatma matnini kiriting"
            return
        }
        Log.d("EEE","$id")
        val data = NoteEntity(0,title,content,data,"", color(id))
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

    override fun updateNote(id: Int, title: String, colorId: Int) {
        val content = editorHelper.getHtml()?: ""
        if (title.isEmpty()){
            showToast.value = "Sarlavhani matnini kriting"
            return
        }
        if (content.isEmpty() || content.contains("<br>")){
            showToast.value = "Eslatma matnini kiriting"
            return
        }
        val data = NoteEntity(id,title,content,data,"", if (colorId == -1) repository.getNoteById(id).background else color(colorId ))
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
            0 -> setColorLiveData.value = Pair(R.drawable.bg_4, 0)
            1 -> setColorLiveData.value = Pair(R.drawable.bg_5, 1)
            2 -> setColorLiveData.value = Pair(R.drawable.bg_6, 2)
            3 -> setColorLiveData.value = Pair(R.drawable.bg_7, 3)
            4 -> setColorLiveData.value = Pair(R.drawable.bg_8, 4)
            5 -> setColorLiveData.value = Pair(R.drawable.bg_9, 5)
            6 -> setColorLiveData.value = Pair(R.drawable.bg_10, 6)
            7 -> setColorLiveData.value = Pair(R.drawable.bg_11, 7)
            8 -> setColorLiveData.value = Pair(R.drawable.bg_12, 8)
            9 -> setColorLiveData.value = Pair(R.drawable.bg_13, 9)
            10 -> setColorLiveData.value = Pair(R.drawable.bg_14, 10)
            11 -> setColorLiveData.value = Pair(R.drawable.bg_15, 11)
            12 -> setColorLiveData.value = Pair(R.drawable.bg_16, 12)

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

    override fun setData() {
        val sfd = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        setDataLiveData.value = sfd.format(Date())
        data = sfd.format(Date())
    }

    private fun color(id: Int): Int{
        return when(id){
            0 -> R.drawable.bg_4
            1 -> R.drawable.bg_5
            2 -> R.drawable.bg_6
            3 -> R.drawable.bg_7
            4 -> R.drawable.bg_8
            5 -> R.drawable.bg_9
            6 -> R.drawable.bg_10
            7 -> R.drawable.bg_11
            8 -> R.drawable.bg_12
            9 -> R.drawable.bg_13
            10 -> R.drawable.bg_14
            11 -> R.drawable.bg_15
            12 -> R.drawable.bg_16
            else -> R.drawable.bg_17
        }
    }
}