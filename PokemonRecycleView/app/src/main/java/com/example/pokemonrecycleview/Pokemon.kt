package com.example.pokemonrecycleview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val imageUrl: String,
    val description: String? = "Ndak Ada"
) : Parcelable
