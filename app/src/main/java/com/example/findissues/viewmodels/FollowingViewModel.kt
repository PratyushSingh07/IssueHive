package com.example.findissues.viewmodels.factory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.Following
import com.example.findissues.repository.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel(
    private val repository: DataRepository
) : ViewModel() {

    private val followingList = MutableLiveData<List<Following>>()

    suspend fun getFollowing() {
        repository.getFollowing().enqueue(object : Callback<List<Following>>{
            override fun onResponse(
                call: Call<List<Following>>,
                response: Response<List<Following>>
            ) {
                followingList.value = response.body()
            }

            override fun onFailure(call: Call<List<Following>>, t: Throwable) {
                Log.d("error","failed in following view model")
            }
        })
    }

    fun observeFollowingList(): LiveData<List<Following>> {
        return followingList
    }

}