package com.example.eslatma.model.room.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.eslatma.model.room.entity.NoteEntity
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 03/04/25
 * Javohir's MacBook Air
 */
@Dao
interface NoteDao {

    @Insert
    fun insertNote(data: NoteEntity): Long

    @Update
    fun updateNote(data: NoteEntity)

   @Delete
    fun deleteNote(data: NoteEntity)

    @Query("select * from `Javohir's Notes` where id = :noteId")
    fun getNoteById(noteId: Int): NoteEntity

    @Query("SELECT * FROM `Javohir's Notes`")
    fun getAllNotes(): LiveData<List<NoteEntity>>


    @Query("SELECT * FROM `Javohir's Notes` WHERE title LIKE '%' || :st || '%' OR content LIKE '%' || :st || '%'")
    fun getSearchNote(st: String): List<NoteEntity>
}