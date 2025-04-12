package com.example.eslatma.app
import AppRepositoryImpl
import android.app.Application
import com.example.eslatma.model.room.dataBase.NoteDataBase


/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 03/04/25
 * Javohir's MacBook Air
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        NoteDataBase.init(this)
        val noteDao = NoteDataBase.database.noteDao()
        AppRepositoryImpl.init(noteDao)
    }
}