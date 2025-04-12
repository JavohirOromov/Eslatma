package com.example.eslatma.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eslatma.databinding.ItemColorBinding
import com.example.eslatma.model.common.ColorData
/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 11/04/25
 * Javohir's MacBook Air
 */
class ColorAdapter: ListAdapter<ColorData, ColorAdapter.VH>(DU) {

    private var itemClickListener: ((Int) -> Unit)? = null

    fun setItemClickListener(itemClickListener: (Int) -> Unit){
        this.itemClickListener = itemClickListener
    }

    inner class VH(private val binding: ItemColorBinding): RecyclerView.ViewHolder(binding.root) {
        private var color: ColorData? = null

        fun bind(data: ColorData) {
            this.color = data
            binding.color.setBackgroundResource(data.color)
        }

        init {
            binding.color.setOnClickListener {
                color?.let {
                    itemClickListener?.invoke(it.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    object DU : DiffUtil.ItemCallback<ColorData>() {
        override fun areItemsTheSame(oldItem: ColorData, newItem: ColorData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ColorData, newItem: ColorData): Boolean {
            return oldItem == newItem
        }
    }
}
