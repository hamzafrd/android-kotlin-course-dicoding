package com.training.dicodingsubmitpart2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.training.dicodingsubmitpart2.Students
import com.training.dicodingsubmitpart2.R

class VerticalAdapter(private var forList: List<Students>) : RecyclerView.Adapter<VerticalAdapter.ViewHolderList>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical, parent, false)
        return ViewHolderList(view)
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        val student = forList[position]
        Glide.with(holder.itemView.context)
            .load(student.image)
            .apply(RequestOptions().override(75, 75))
            .into(holder.image)
        holder.apply{
            nama.text = student.nama
            kelas.text = student.kelas
        }

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(forList[holder.bindingAdapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Students)
    }

    override fun getItemCount(): Int {
        return forList.size
    }

    inner class ViewHolderList(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nama: TextView = itemView.findViewById(R.id.nama_row)
        var kelas: TextView = itemView.findViewById(R.id.kelas_row)
        var image: ImageView = itemView.findViewById(R.id.image_row)
    }
}