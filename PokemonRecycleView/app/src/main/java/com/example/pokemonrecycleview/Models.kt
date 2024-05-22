package com.example.pokemonrecycleview

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val results: List<PokemonListItem>
)

data class PokemonDetailsResponse(
    val name: String,
    @SerializedName("sprites") val spriteUrls: SpriteUrls,
    @SerializedName("species") val speciesUrl: SpeciesUrl
)

data class SpriteUrls(
    @SerializedName("front_default") val frontDefault: String
)

data class SpeciesUrl(
    val url: String
)

data class PokemonListItem(
    val name: String,
    val url: String
)