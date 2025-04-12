import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eslatma.screen.note.NoteViewModelImpl
import jp.wasabeef.richeditor.RichEditor
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 10/04/25
 * Javohir's MacBook Air
 */
class NoteViewModelFactory(
    private val editor: RichEditor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val editorHelper = RichEditorHelpIml(editor)
        return NoteViewModelImpl(editorHelper) as T
    }
}