package com.example.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListHeroAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    //Berguna sebagai wrapper yang berisi setiap item dalam daftar RecyclerView
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    //Membuat List Data/ ViewHolder dari layaout tertentu tapi belum ada datanya
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHeroAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    //Menampilkan berapa banyak data ? disni kita menampilkan semua
    override fun getItemCount(): Int {
        return listHero.size
    }

    //mengisi data dari viewHolder baru yang telah dibuat onCreate ViewHolder
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, desc, photo) = listHero[position]
        holder.apply {
//            imgPhoto.setImageResource(photo)
            Glide.with(this.itemView.context)
                .load(photo) // URL Gambar
                .circleCrop()
                .into(holder.imgPhoto) // imageView mana yang akan diterapkan
            tvName.text = name
            tvDesc.text = desc
//            Toast.makeText(itemView.context, "Kamu Memilih : ${listHero[adapterPosition].name}", Toast.LENGTH_SHORT).show()
            itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[this.adapterPosition]) }
        }
    }

}