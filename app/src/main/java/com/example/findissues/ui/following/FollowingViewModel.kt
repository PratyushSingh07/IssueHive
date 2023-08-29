package com.example.findissues.ui.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.repository.DataRepository
import com.example.findissues.utils.FollowingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val followingList = MutableStateFlow<FollowingUiState>(FollowingUiState.Loading)

    fun getFollowing() {
        viewModelScope.launch {
            followingList.value = FollowingUiState.Loading
            repository.getFollowing().collect {
                followingList.value = FollowingUiState.FollowingList(it)
            }
        }
    }

    fun observeFollowingList(): StateFlow<FollowingUiState> {
        return followingList
    }

}