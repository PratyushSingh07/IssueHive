package com.example.findissues.ui.issues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findissues.repository.DataRepository
import com.example.findissues.utils.IssuesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var issueLiveData = MutableStateFlow<IssuesUiState>(IssuesUiState.Loading)
    private val tags =
        listOf("Kotlin", "Java", "Python", "Rust", "React.js", "Go", "Javascript", "Node.js")
    var tag = ""
        private set

    fun getTags() = tags
    fun getIssueLink() {
        viewModelScope.launch {
            issueLiveData.value = IssuesUiState.Loading
            repository.getAllIssues(tag).catch {
                issueLiveData.value = IssuesUiState.Error
            }.collect {
                issueLiveData.value = IssuesUiState.ListIssues(it.items)
            }
        }
    }

    fun tagSelected(selectedTag: String) {
        tag = selectedTag
        getIssueLink()
    }

    fun observeIssueLiveData(): StateFlow<IssuesUiState> {
        return issueLiveData
    }
}