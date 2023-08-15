package com.example.findissues.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.models.issues.IssuesList
import com.example.findissues.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var issueLiveData = MutableLiveData<List<IssuesList>>()

    fun getIssueLink() {
        viewModelScope.launch {
            val response = repository.getAllIssues()
            if (response.isSuccessful) {
                issueLiveData.value = response.body()?.items
            }
        }
    }

    fun observeIssueLiveData(): LiveData<List<IssuesList>> {
        return issueLiveData
    }
}