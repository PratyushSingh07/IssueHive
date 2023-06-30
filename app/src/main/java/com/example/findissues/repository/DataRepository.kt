package com.example.findissues.repository

import com.example.findissues.api.GithubApiService
import com.example.findissues.utils.Constants
import javax.inject.Inject

class DataRepository @Inject constructor(private val service: GithubApiService) {
    suspend fun getAllIssues() = service.getIssue("kotlin", Constants.CREATED)
    suspend fun getPinnedRepos() = service.getPinnedRepos("pratyushsingh07")
    suspend fun getUser() = service.getUser("pratyushsingh07")
    suspend fun getFollowers() = service.getFollowers("pratyushsingh07")
    suspend fun getFollowing() = service.getFollowings("pratyushsingh07")
}