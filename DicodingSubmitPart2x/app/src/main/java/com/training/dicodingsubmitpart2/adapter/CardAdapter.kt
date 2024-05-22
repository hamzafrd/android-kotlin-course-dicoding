package com.training.dicodingsubmitpart2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.training.dicodingsubmitpart2.R
import com.training.dicodingsubmitpart2.Students

class CardAdapter(private val listStudents: ArrayList<Students>) : RecyclerView.Adapter<CardAdapter.CardViewViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val murid = listStudents[position]
        Glide.with(holder.itemView.context)
            .load(murid.image)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
        holder.tvName.text = murid.nama
        holder.kelas.text = murid.kelas
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listStudents[holder.bindingAdapterPosition]) }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Students)
    }

    override fun getItemCount(): Int {
        return listStudents.size
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var kelas: TextView = itemView.findViewById(R.id.tv_item_detail)
    }
}