package com.example.findissues.repository

import com.example.findissues.api.github.GithubApiService
import com.example.findissues.models.home.Followers
import com.example.findissues.models.home.Following
import com.example.findissues.models.issues.Issues
import com.example.findissues.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataRepository @Inject constructor(private val service: GithubApiService) {

    suspend fun getAllIssues(tag:String): Flow<Issues> = flow {
        emit(service.getIssue(tag, Constants.CREATED))
    }

    suspend fun getUser() = service.getUser("pratyushsingh07")

    suspend fun getFollowers(): Flow<List<Followers>> {
        return flow {
            emit(service.getFollowers("pratyushsingh07"))
        }
    }

    suspend fun getFollowing(): Flow<List<Following>> {
        return flow {
            emit(service.getFollowings("pratyushsingh07"))
        }
    }

    suspend fun getRepos() = service.getRepos("pratyushsingh07")
}