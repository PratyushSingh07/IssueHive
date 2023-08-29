package com.example.findissues.ui.issues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.repository.DataRepository
import com.example.findissues.utils.IssuesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var issueLiveData = MutableStateFlow<IssuesUiState>(IssuesUiState.Loading)

    fun getIssueLink() {
        viewModelScope.launch {
            issueLiveData.value = IssuesUiState.Loading
            repository.getAllIssues().collect {
                issueLiveData.value = IssuesUiState.ListIssues(it.items)
            }
        }
    }

    fun observeIssueLiveData(): StateFlow<IssuesUiState> {
        return issueLiveData
    }
}