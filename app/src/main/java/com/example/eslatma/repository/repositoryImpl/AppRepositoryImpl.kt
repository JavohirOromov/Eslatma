
import androidx.lifecycle.LiveData
import com.example.eslatma.model.room.dao.NoteDao
import com.example.eslatma.model.room.entity.NoteEntity
import com.example.eslatma.repository.repository.AppRepository

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 03/04/25
 * Javohir's MacBook Air
 */
class AppRepositoryImpl(private val noteDao: NoteDao): AppRepository {

    companion object{
        private lateinit var instance: AppRepository

        fun init(noteDao: NoteDao){
            if (!(::instance.isInitialized)){
                instance = AppRepositoryImpl(noteDao)
            }
        }
        fun getInstance(): AppRepository{
            return instance
        }
    }

    override fun addNote(data: NoteEntity): Long {
        return noteDao.insertNote(data)
    }

    override fun updateNote(data: NoteEntity) {
        noteDao.updateNote(data)
    }

    override fun deleteNote(data: NoteEntity) {
        noteDao.deleteNote(data)
    }

    override fun getNoteById(id: Int): NoteEntity {
       return noteDao.getNoteById(id)
    }

    override fun getAllNotes(): LiveData<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

    override fun getSearchNotes(query: String): List<NoteEntity> {
        return noteDao.getSearchNote(query)
    }
}