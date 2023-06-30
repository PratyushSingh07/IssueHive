package com.example.findissues.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.Followers
import com.example.findissues.repository.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel(
    private val repository: DataRepository
) : ViewModel() {
    private var followersLiveData = MutableLiveData<List<Followers>>()

    suspend fun getFollowers() {
        repository.getFollowers().enqueue(object : Callback<List<Followers>> {
            override fun onResponse(
                call: Call<List<Followers>>,
                response: Response<List<Followers>>
            ) {
                if (response.body() != null) {
                    followersLiveData.value = response.body()
                    Log.d("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<List<Followers>>, t: Throwable) {
                Log.d("error", t.toString())
            }

        })
    }

    fun observeFollowersLiveData(): LiveData<List<Followers>> {
        return followersLiveData
    }
}