package com.example.findissues.api

import com.example.findissues.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceHandler {

    val pinnedRepoService: GithubApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.PINNED_REPO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)
    }
}