package com.example.findissues.repository

import com.example.findissues.api.profile.ProfileStatsService
import javax.inject.Inject

class ProfileStatsRepository @Inject constructor(private val service: ProfileStatsService) {

    fun getProfileSummary() = service.getProfileSummary("pratyushsingh07", "github_dark")

}