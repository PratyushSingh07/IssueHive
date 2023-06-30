package com.example.findissues.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findissues.repository.DataRepository
import com.example.findissues.viewmodels.FollowersViewModel

class FollowersViewModelFactory constructor(private val repository: DataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FollowersViewModel::class.java)) {
            FollowersViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}