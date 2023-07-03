package com.example.findissues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findissues.models.home.Repository
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var repoLiveData = MutableLiveData<List<Repository>>()

    suspend fun getRepos() {
        repository.getRepos().enqueue(object : Callback<List<Repository>> {
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                if (response.body() != null)
                    repoLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {

            }

        })
    }
    fun observeRepoLiveData() : LiveData<List<Repository>> {
        return repoLiveData
    }
}