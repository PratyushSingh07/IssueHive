package com.example.findissues.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.IssuesList
import com.example.findissues.models.User
import com.example.findissues.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel constructor(
    private val repository: UserRepository
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

    fun observeIssueLiveData(): LiveData<User> {
        return userLiveData
    }
}