package com.example.findissues.repository

import com.example.findissues.api.pinned.PinnedRepoService
import javax.inject.Inject

class PinnedRepository @Inject constructor(private val service: PinnedRepoService) {

    fun getPinnedRepos() = service.getPinnedRepos("pratyushsingh07")
}