package com.example.findissues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.models.home.Followers
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private var followersLiveData = MutableLiveData<List<Followers>>()

    fun getFollowers() {
        viewModelScope.launch {
            val response = repository.getFollowers()
            if (response.isSuccessful) {
                followersLiveData.value = response.body()
            }
        }
    }

    fun observeFollowersLiveData(): LiveData<List<Followers>> {
        return followersLiveData
    }
}