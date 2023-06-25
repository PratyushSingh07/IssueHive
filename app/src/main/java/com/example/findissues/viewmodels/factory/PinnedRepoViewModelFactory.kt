package com.example.findissues.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findissues.repository.DataRepository
import com.example.findissues.viewmodels.PinnedRepoViewModel

class PinnedRepoViewModelFactory constructor(private val repository: DataRepository) :
    ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PinnedRepoViewModel::class.java)) {
            PinnedRepoViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}