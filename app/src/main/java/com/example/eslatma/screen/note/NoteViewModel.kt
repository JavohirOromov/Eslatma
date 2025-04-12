package com.example.eslatma.screen.note
import androidx.lifecycle.LiveData

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 02/04/25
 * Javohir's MacBook Air
 */
interface NoteViewModel {

    val openMainLiveDate: LiveData<Unit>
    val visibilityDeleteBtn: LiveData<Boolean>
    val visibilityUpdateBtn: LiveData<Boolean>
    val visibilitySaveBtn: LiveData<Boolean>
    val noteTitleSetTextLiveData: LiveData<String>
    val noteContentSetTextLiveData: LiveData<String>
    val setBackgroundLiveData: LiveData<Int>
    val clearOldStateLiveData: LiveData<Unit>
    val showBottomSheetLiveData: LiveData<Unit>
    val setColorLiveData: LiveData<Int>




    fun openMainScreen()
    fun saveNote(title: String)
    fun setId(id: Int)
    fun updateNote(id: Int, title: String)
    fun showBottomSheet()
    fun deleteNote(id: Int)
    fun setColor(id: Int)
    fun setBold()
    fun setItalic()
    fun setUnderline()
    fun setBulletList()
    fun setNumberedList()
    fun setCheckbox()
    fun formatBtn(index: Int)
}