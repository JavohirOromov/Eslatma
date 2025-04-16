package com.example.eslatma.screen.main
import androidx.lifecycle.LiveData
import com.example.eslatma.model.room.entity.NoteEntity

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 02/04/25
 * Javohir's MacBook Air
 */
interface MainViewModel {

    val openNoteLiveData: LiveData<Unit>
    val allNotesLiveData: LiveData<List<NoteEntity>>
    val updateNoteLiveData: LiveData<Int>
    val searchQueryObserver: LiveData<List<NoteEntity>>
    val setInputSearchQuery: LiveData<String>


    fun openNoteScreen()
    fun updateLiveData(id: Int)
    fun searchQuery(query: String)
    fun setVoiceResult(query: String)
}