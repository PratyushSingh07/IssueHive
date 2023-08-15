package com.example.findissues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.models.home.Repository
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var repoLiveData = MutableLiveData<List<Repository>>()

    fun getRepos() {
        viewModelScope.launch {
            val response = repository.getRepos()
            if (response.isSuccessful) {
                repoLiveData.value = response.body()
            }
        }
    }

    fun observeRepoLiveData(): LiveData<List<Repository>> {
        return repoLiveData
    }
}