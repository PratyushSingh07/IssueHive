package com.example.findissues.utils

import com.example.findissues.models.home.Following

sealed class FollowingUiState {
    object Loading : FollowingUiState()
    data class FollowingList(val followingList: List<Following>) : FollowingUiState()
}
