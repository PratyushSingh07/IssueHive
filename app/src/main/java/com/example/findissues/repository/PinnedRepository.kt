package com.example.findissues.repository

import com.example.findissues.api.PinnedRepoService
import javax.inject.Inject

class PinnedRepository @Inject constructor(private val service: PinnedRepoService) {

    fun getPinnedRepos() = service.getPinnedRepos("pratyushsingh07")
}