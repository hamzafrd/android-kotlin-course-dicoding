package com.spnj.notes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.spnj.notes.databinding.ListRecycleBinding

class AdapterViewHolderActivity(
    private val binding: ListRecycleBinding,
    listener: AdapterActivity.OnItemClickListener,
    longListener: AdapterActivity.OnItemLongClickListener,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            listener.onItemclick(adapterPosition)
        }
        itemView.setOnLongClickListener {
            longListener.onItemclick(adapterPosition)
            true
        }
    }

    fun setData(data: DataClassActitivity) {
//        binding.judulList.text = data.judul
//        binding.catatanList.text = data.catatan
        binding.apply {
            judulRv.text = data.judul
            catatanRv.text = data.catatan
        }
    }
}
