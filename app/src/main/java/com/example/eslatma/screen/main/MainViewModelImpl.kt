package com.example.eslatma.screen.main
import AppRepositoryImpl
import androidx.lifecycle.MediatorLiveData
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
    override val allNotesLiveData = MediatorLiveData<List<NoteEntity>>()
    override val updateNoteLiveData = MutableLiveData<Int>()
    override val searchQueryObserver = MutableLiveData<List<NoteEntity>>()
    override val setInputSearchQuery = MutableLiveData<String>()



    init {
        allNotesLiveData.addSource(repository.getAllNotes()){
            allNotesLiveData.value = it
        }
    }

    override fun openNoteScreen() {
        openNoteLiveData.value = Unit
    }


    override fun updateLiveData(id: Int) {
        updateNoteLiveData.value = id
    }

    override fun searchQuery(query: String) {
        searchQueryObserver.value = repository.getSearchNotes(query)
    }

    override fun setVoiceResult(query: String) {
        searchQueryObserver.value = repository.getSearchNotes(query)
        setInputSearchQuery.value = query
    }
}