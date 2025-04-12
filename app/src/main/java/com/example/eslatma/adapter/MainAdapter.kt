import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eslatma.databinding.ItemMainBinding
import com.example.eslatma.model.room.entity.NoteEntity
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 06/04/25
 * Javohir's MacBook Air
 */
class MainAdapter: ListAdapter<NoteEntity,MainAdapter.VH>(DU) {

    private var itemClickListener: ((Int) -> Unit)? = null

    fun setItemClickListener(itemClickListener: (Int) -> Unit){
        this.itemClickListener = itemClickListener
    }


    inner class VH(private val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root){
        private var note: NoteEntity? = null

        fun bind(data: NoteEntity){
            this.note = data
            binding.title.text = data.title
            binding.titleText.text = data.content
        }

        init {
            binding.item.setOnClickListener {
                note?.let {
                    itemClickListener?.invoke(it.id)
                }
            }
        }
    }


    object DU: DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}