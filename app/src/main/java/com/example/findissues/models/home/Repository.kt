package com.example.findissues.models.home


data class Repository(
    val name: String,
    val stargazers_count: Int,
    val language: String,
    val html_url: String,
    val description: String
)
