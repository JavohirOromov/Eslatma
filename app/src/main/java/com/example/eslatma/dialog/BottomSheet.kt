
import android.content.Context
import android.view.LayoutInflater
import com.example.eslatma.R
import com.example.eslatma.adapter.ColorAdapter
import com.example.eslatma.databinding.BottomColorBinding
import com.example.eslatma.model.common.ColorData
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 11/04/25
 * Javohir's MacBook Air
 */
class BottomSheet(context: Context): BottomSheetDialog(context) {
    private val binding: BottomColorBinding = BottomColorBinding.inflate(LayoutInflater.from(context))
    private lateinit var list: ArrayList<ColorData>
    private val adapter by lazy { ColorAdapter() }

    private var colorClickListener: ((Int) ->Unit)? = null

    fun setColorClickListener(colorClickListener: (Int) -> Unit){
        this.colorClickListener = colorClickListener
    }

    init {
        loadList()
        setContentView(binding.root)
        binding.listColor.adapter = adapter
        adapter.submitList(list)

        adapter.setItemClickListener {
            colorClickListener?.invoke(it)
            dismiss()
        }
    }

    private fun loadList(){
        list = ArrayList()
        list.addAll(listOf(
            ColorData(0, R.drawable.bg_4),
            ColorData(1, R.drawable.bg_5),
            ColorData(2, R.drawable.bg_6),
            ColorData(3, R.drawable.bg_7),
            ColorData(4, R.drawable.bg_8),
            ColorData(5, R.drawable.bg_9),
            ColorData(6, R.drawable.bg_10),
            ColorData(7, R.drawable.bg_11),
            ColorData(8, R.drawable.bg_12),
            ColorData(9, R.drawable.bg_13),
            ColorData(10, R.drawable.bg_14),
            ColorData(11, R.drawable.bg_15),
            ColorData(12,R.drawable.bg_16)
        ))
    }
}