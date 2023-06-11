package com.example.findissues.api

import retrofit2.http.GET
import retrofit2.http.Query


//https://api.github.com/search/issues?q=language:${params.lang}+is:issue+is:open+no:assignee+created:%3E=2023-05-20&sort:created

interface GithubApiService {

    @GET("/search/issues?")
    fun getIssue(
        @Query("q") language: String,
        @Query("sort") sort: String
    )
}