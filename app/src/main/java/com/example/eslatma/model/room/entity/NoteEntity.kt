package com.example.eslatma.model.room.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 03/04/25
 * Javohir's MacBook Air
 */
@Entity(tableName = "Javohir's Notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val createAt: String,
    val updateAt: String,
    val background: Int,
)