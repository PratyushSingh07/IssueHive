package com.example.findissues.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.home.PinnedRepo
import com.example.findissues.repository.DataRepository
import com.example.findissues.repository.PinnedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PinnedRepoViewModel @Inject constructor(
    private val repository: PinnedRepository
) : ViewModel() {
    private var pinnedRepoLiveData = MutableLiveData<List<PinnedRepo>>()

    suspend fun getPinnedRepos() {
        repository.getPinnedRepos().enqueue(object : Callback<List<PinnedRepo>> {
            override fun onResponse(call: Call<List<PinnedRepo>>, response: Response<List<PinnedRepo>>) {
                pinnedRepoLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<PinnedRepo>>, t: Throwable) {
                Log.e("failure",t.toString())
            }

        })
    }

    fun observePinnedRepoLiveData(): LiveData<List<PinnedRepo>> {
        return pinnedRepoLiveData
    }
}