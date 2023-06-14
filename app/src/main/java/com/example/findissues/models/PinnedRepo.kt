package com.example.findissues.models

data class PinnedRepo(
    val repo: String,
    val owner: String,
    val description: String,
    val language: String,
    val stars: Int,
    val image: String,
    val link: String
)

