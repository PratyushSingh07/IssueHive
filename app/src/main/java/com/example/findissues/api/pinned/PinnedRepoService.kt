package com.example.findissues.api.pinned

import com.example.findissues.models.home.PinnedRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PinnedRepoService {

    @GET("/users={username}")
    fun getPinnedRepos(
        @Query("username") username: String
    ): Call<List<PinnedRepo>>

}