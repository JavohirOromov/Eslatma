package com.example.eslatma.repository.repository
import com.example.eslatma.model.room.entity.NoteEntity

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 03/04/25
 * Javohir's MacBook Air
 */


interface AppRepository {

    fun addNote(data: NoteEntity): Long

    fun updateNote(data: NoteEntity)

    fun deleteNote(data: NoteEntity)

    fun getNoteById(id: Int): NoteEntity

    fun getAllNotes(): List<NoteEntity>
}