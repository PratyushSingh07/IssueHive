package com.example.findissues.api

import com.example.findissues.models.Issues
import com.example.findissues.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//https://api.github.com/search/issues?q=language:${params.lang}+is:issue+is:open+no:assignee+created:%3E=2023-05-20&sort:created
// https://api.github.com/repos/openmf/mifos-mobile/pulls?state=open -> all open PRs
// https://api.github.com/repos/openmf/mifos-mobile/pulls?state=all -> open/closed PRs

interface GithubApiService {

    @GET("/search/issues?")
    fun getIssue(
        @Query("q") language: String,
        @Query("sort") sort: String
    ): Call<Issues>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Call<User>

}