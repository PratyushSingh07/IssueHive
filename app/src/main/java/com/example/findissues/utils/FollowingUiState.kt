package com.example.findissues.utils

import com.example.findissues.models.home.Following

sealed class FollowingUiState {
    object Loading : FollowingUiState()
    object Error : FollowingUiState()
    data class FollowingList(val followingList: List<Following>) : FollowingUiState()
}
