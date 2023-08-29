package com.example.findissues.ui.followers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.repository.DataRepository
import com.example.findissues.utils.FollowersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private var followersLiveData = MutableStateFlow<FollowersUiState>(FollowersUiState.Loading)

    fun getFollowers() {
        viewModelScope.launch {
            followersLiveData.value = FollowersUiState.Loading
            repository.getFollowers().catch {
                followersLiveData.value = FollowersUiState.Error
            }.collect {
                followersLiveData.value = FollowersUiState.FollowersList(it)
            }
        }
    }

    fun observeFollowersLiveData(): StateFlow<FollowersUiState> {
        return followersLiveData
    }
}