package com.example.findissues.utils

import com.example.findissues.models.issues.IssuesList

sealed class IssuesUiState{
    object Loading: IssuesUiState()
    data class ListIssues(val issuesList: List<IssuesList>): IssuesUiState()
}
