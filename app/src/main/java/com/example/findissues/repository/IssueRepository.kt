package com.example.findissues.repository

import com.example.findissues.api.GithubApiService
import com.example.findissues.utils.Constants.CREATED

class IssueRepository constructor(private val service: GithubApiService) {
    fun getAllIssues() = service.getIssue("kotlin", CREATED)
}