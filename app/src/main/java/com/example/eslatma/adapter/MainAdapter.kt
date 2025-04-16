package com.example.eslatma.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eslatma.R
import com.example.eslatma.databinding.ItemMainBinding
import com.example.eslatma.model.room.entity.NoteEntity
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 06/04/25
 * Javohir's MacBook Air
 */
class MainAdapter: ListAdapter<NoteEntity, MainAdapter.VH>(DU) {

    private var itemClickListener: ((Int) -> Unit)? = null

    fun setItemClickListener(itemClickListener: (Int) -> Unit){
        this.itemClickListener = itemClickListener
    }


    inner class VH(private val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root){
        private var note: NoteEntity? = null


        fun bind(data: NoteEntity){
            this.note = data
            binding.title.text = data.title
            binding.date.text = data.createAt
            val centeredContent = "<div style='text-align:center;'>${data.content}</div>"
            binding.titleText.html = centeredContent
            binding.item.setBackgroundResource(data.background)
            binding.titleText.setEditorBackgroundColor(color(data.id))
            binding.titleText.setTextColor(color(data.id))
            binding.titleText.setEditorFontSize(18)
            binding.titleText.setEditorFontColor(Color.WHITE)
            binding.titleText.setInputEnabled(false)
        }

        init {
            binding.item.setOnClickListener {
                note?.let {
                    itemClickListener?.invoke(it.id)
                }
            }
        }
    }

    private fun color(id: Int): Int{
        return when(id){
            0 -> R.drawable.bg_4
            1 -> R.drawable.bg_5
            2 -> R.drawable.bg_6
            3 -> R.drawable.bg_7
            4 -> R.drawable.bg_8
            5 -> R.drawable.bg_9
            6 -> R.drawable.bg_10
            7 -> R.drawable.bg_11
            8 -> R.drawable.bg_12
            9 -> R.drawable.bg_13
            10 -> R.drawable.bg_14
            11 -> R.drawable.bg_15
            12 -> R.drawable.bg_16
            else -> R.drawable.bg_17
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