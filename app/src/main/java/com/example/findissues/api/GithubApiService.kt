package com.example.findissues.api

import com.example.findissues.models.Issues
import com.example.findissues.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.github.com/search/issues?q=language:${params.lang}+is:issue+is:open+no:assignee+created:%3E=2023-05-20&sort:created

interface GithubApiService {

    @GET("/search/issues?")
    fun getIssue(
        @Query("q") language: String,
        @Query("sort") sort: String
    ): Call<Issues>

}