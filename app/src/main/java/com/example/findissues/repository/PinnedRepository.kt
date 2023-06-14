package com.example.findissues.repository

import com.example.findissues.api.GithubApiService

class PinnedRepository constructor(private val service: GithubApiService) {
    suspend fun getPinnedRepos() = service.getPinnedRepos("pratyushsingh07")
}