package com.example.findissues.utils

import com.example.findissues.models.home.Followers

sealed class FollowersUiState {
    object Loading : FollowersUiState()
    object Error : FollowersUiState()
    data class FollowersList(val followersList: List<Followers>) : FollowersUiState()
}
