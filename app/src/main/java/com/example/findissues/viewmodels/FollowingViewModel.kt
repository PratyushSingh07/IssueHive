package com.example.findissues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.models.home.Following
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val followingList = MutableLiveData<List<Following>>()

    fun getFollowing() {
        viewModelScope.launch {
            val response = repository.getFollowing()
            if (response.isSuccessful) {
                followingList.value = response.body()
            }
        }
    }

    fun observeFollowingList(): LiveData<List<Following>> {
        return followingList
    }

}