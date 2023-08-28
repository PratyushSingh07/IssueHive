package com.example.findissues.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.models.home.Following
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val followingList = MutableStateFlow<List<Following>>(emptyList())

    fun getFollowing() {
        viewModelScope.launch {
            followingList.value = repository.getFollowing()
        }
    }

    fun observeFollowingList(): StateFlow<List<Following>> {
        return followingList
    }

}