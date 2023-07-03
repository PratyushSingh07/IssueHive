package com.example.findissues.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.home.User
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var userLiveData = MutableLiveData<User>()

    suspend fun getUserDetail() {
        repository.getUser().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.body() != null) {
                    userLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("error", "failure")
            }

        })
    }

    fun observeUserLiveData(): LiveData<User> {
        return userLiveData
    }
}