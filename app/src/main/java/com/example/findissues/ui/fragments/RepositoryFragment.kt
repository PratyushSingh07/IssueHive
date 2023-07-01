package com.example.findissues.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.R
import com.example.findissues.databinding.FragmentRepositoryBinding
import com.example.findissues.ui.adapters.RepositoryAdapter
import com.example.findissues.viewmodels.RepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class RepositoryFragment : Fragment() {

    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    lateinit var repositoryViewModel: RepositoryViewModel

    @Inject
    lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater,container,false)
        binding.rvRepository.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = repositoryAdapter
        }
        repositoryViewModel = ViewModelProvider(this)[RepositoryViewModel::class.java]
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                repositoryViewModel.getRepos()
            }
        }
        repositoryViewModel.observeRepoLiveData().observe(viewLifecycleOwner) {
            repositoryAdapter.setUpRepositoryList(it)
        }

        return binding.root
    }
}