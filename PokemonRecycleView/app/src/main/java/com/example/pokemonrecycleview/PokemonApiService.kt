package com.example.pokemonrecycleview

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<PokemonDetailsResponse>

    @GET("pokemon-species/{name}/")
    suspend fun getPokemonDescription(@Path("name") name: String): Response<PokemonSpeciesResponse>

    companion object {
        fun create(): PokemonApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PokemonApiService::class.java)
        }
    }
}