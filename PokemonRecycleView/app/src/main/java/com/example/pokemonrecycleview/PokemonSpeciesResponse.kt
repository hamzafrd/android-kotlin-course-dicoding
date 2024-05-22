package com.example.pokemonrecycleview

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpeciesResponse(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryResponse>
) : Parcelable {

    @Parcelize
    data class FlavorTextEntryResponse(
        @SerializedName("flavor_text")
        val flavorText: String,
        @SerializedName("language")
        val language: LanguageResponse
    ) : Parcelable {

        @Parcelize
        data class LanguageResponse(
            @SerializedName("name")
            val name: String
        ) : Parcelable
    }
}
