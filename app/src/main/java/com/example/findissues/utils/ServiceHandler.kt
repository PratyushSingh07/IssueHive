package com.example.findissues.utils

import com.example.findissues.api.GithubApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceHandler {
    val apiService: GithubApiService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(GithubApiService::class.java)
    }
}