package com.example.eslatma.model.room.dataBase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eslatma.model.room.dao.NoteDao
import com.example.eslatma.model.room.entity.NoteEntity

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 03/04/25
 * Javohir's MacBook Air
 */
@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        lateinit var database: NoteDataBase
            private set

        fun init(context: Context){
            if (!(::database.isInitialized)){
                database = Room.databaseBuilder(context,NoteDataBase::class.java,"Javohir's Notes")
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}