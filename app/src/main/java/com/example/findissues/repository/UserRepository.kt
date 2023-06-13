package com.example.findissues.repository

import com.example.findissues.api.GithubApiService

class UserRepository constructor(private val service: GithubApiService) {
    suspend fun getUser() = service.getUser("pratyushsingh07")
}