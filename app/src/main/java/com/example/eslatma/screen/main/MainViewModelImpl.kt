package com.example.eslatma.screen.main
import AppRepositoryImpl
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eslatma.model.room.entity.NoteEntity
import com.example.eslatma.repository.repository.AppRepository

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 02/04/25
 * Javohir's MacBook Air
 */
class MainViewModelImpl: MainViewModel, ViewModel() {
    private val repository: AppRepository = AppRepositoryImpl.getInstance()
    override val openNoteLiveData = MutableLiveData<Unit>()
    override val allNotesLiveData = MutableLiveData<List<NoteEntity>>()
    override val updateNoteLiveData = MutableLiveData<Int>()

    override fun openNoteScreen() {
        openNoteLiveData.value = Unit
    }

    override fun allNotes() {
        allNotesLiveData.value = repository.getAllNotes()
    }

    override fun updateLiveData(id: Int) {
        updateNoteLiveData.value = id
    }
}