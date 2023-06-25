package com.example.findissues.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.PinnedRepo
import com.example.findissues.repository.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PinnedRepoViewModel constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var pinnedRepoLiveData = MutableLiveData<PinnedRepo>()

    suspend fun getPinnedRepos() {
        repository.getPinnedRepos().enqueue(object : Callback<PinnedRepo> {
            override fun onResponse(call: Call<PinnedRepo>, response: Response<PinnedRepo>) {
                pinnedRepoLiveData.value = response.body()
             }

            override fun onFailure(call: Call<PinnedRepo>, t: Throwable) {
                Log.d("failure","failure in PinnedRepoViewModel")
            }

        })
    }

    fun observePinnedRepoLiveData(): LiveData<PinnedRepo> {
        return pinnedRepoLiveData
    }
}