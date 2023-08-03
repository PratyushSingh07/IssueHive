package com.example.findissues.models.issues

data class IssuesList(
    val url: String,
    val repository_url: String,
    val labels_url: String,
    val comments_url: String,
    val html_url: String
)
