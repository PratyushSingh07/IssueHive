package com.example.findissues.api

import com.example.findissues.models.*
import com.example.findissues.models.home.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//https://api.github.com/search/issues?q=language:${params.lang}+is:issue+is:open+no:assignee+created:%3E=2023-05-20&sort:created
// https://api.github.com/repos/openmf/mifos-mobile/pulls?state=open -> all open PRs
// https://api.github.com/repos/openmf/mifos-mobile/pulls?state=all -> open/closed PRs
//https://api.github.com/users/pratyushsingh07/followers?per_page=100 -> followers
//https://api.github.com/users/pratyushsingh07/following?per_page=100 -> following
// https://api.github.com/users/pratyushsingh07/repos -> repos of user
// https://api.github.com/users/pratyushsingh07/starred?per_page=40 -> starred repos of a user

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

    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<Followers>>

    @GET("/users/{username}/following")
    fun getFollowings(
        @Path("username") username: String
    ): Call<List<Following>>

    @GET("/users/{username}/repos")
    fun getRepos(
        @Path("username") username: String
    ): Call<List<Repository>>

}