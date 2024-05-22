package com.example.pokemonrecycleview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonList = mutableListOf<Pokemon>()
        val adapter = PokemonListAdapter(pokemonList)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val newList = getPokemonList()
            withContext(Dispatchers.Main) {
                pokemonList.clear()
                pokemonList.addAll(newList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private suspend fun getPokemonList(): List<Pokemon> {
        val pokemonApiService = PokemonApiService.create()
        val response = pokemonApiService.getPokemonList()
        if (response.isSuccessful) {
            val pokemonList = response.body()?.results
            return pokemonList?.map { pokemonListItem ->
                val name = pokemonListItem.name
                val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getIdFromUrl(pokemonListItem.url)}.png"
                val detailsResponse = pokemonApiService.getPokemonDetails(name)
                if (detailsResponse.isSuccessful) {
                    val descriptionUrl = detailsResponse.body()?.speciesUrl?.url
                    val descriptionResponse = descriptionUrl?.let { pokemonApiService.getPokemonDescription(it) }
                    val description = descriptionResponse?.body()?.flavorTextEntries?.find { it.language.name == "en" }?.flavorText ?: ""
                    Pokemon(name, imageUrl, description)
                } else {
                    Pokemon(name, imageUrl)
                }
            } ?: emptyList()
        } else {
            return emptyList()
        }
    }

    private fun getIdFromUrl(url: String): Int {
        val regex = "https://pokeapi.co/api/v2/pokemon/(\\d+)/".toRegex()
        return regex.find(url)?.groupValues?.getOrNull(1)?.toInt() ?: 0
    }
}