package com.example.findissues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.models.home.User
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var userLiveData = MutableLiveData<User>()

    fun getUserDetail() {
        viewModelScope.launch {
            val response = repository.getUser()
            if (response.isSuccessful) {
                userLiveData.value = response.body()
            }
        }
    }

    fun observeUserLiveData(): LiveData<User> {
        return userLiveData
    }
}