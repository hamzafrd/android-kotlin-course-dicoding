package com.example.githubusers.data.api

import com.example.githubusers.data.api.model.DetailGithubUser
import com.example.githubusers.data.api.model.GithubResponse
import com.example.githubusers.data.api.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String,
        @Query("per_page") perPage: Int
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailGithubUser>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String,
    ): Call<List<GithubUser>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String,
    ): Call<List<GithubUser>>
}