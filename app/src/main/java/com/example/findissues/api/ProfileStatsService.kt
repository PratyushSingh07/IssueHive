package com.example.findissues.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileStatsService {

    @GET("cards/profile-details")
    fun getProfileSummary(
        @Query("username") username: String,
        @Query("theme") theme_name: String
    ): Call<ResponseBody?>
}