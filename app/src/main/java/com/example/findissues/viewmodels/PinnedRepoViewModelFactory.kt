package com.example.findissues.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findissues.repository.PinnedRepository

class PinnedRepoViewModelFactory constructor(private val repository: PinnedRepository) :
    ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PinnedRepoViewModel::class.java)) {
            PinnedRepoViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}