package com.example.pokemonrecycleview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

    fun bind(pokemon: Pokemon) {
        nameTextView.text = pokemon.name
        Glide.with(itemView).load(pokemon.imageUrl).placeholder(R.drawable.placeholder).into(imageView)
        descriptionTextView.text = pokemon.description
    }
}
