package com.example.findissues.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.R
import com.example.findissues.api.ServiceHandler
import com.example.findissues.databinding.FragmentIssuesBinding
import com.example.findissues.repository.DataRepository
import com.example.findissues.ui.adapters.IssuesAdapter
import com.example.findissues.viewmodels.factory.IssueViewModelFactory
import com.example.findissues.viewmodels.IssuesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IssuesFragment : Fragment() {

    private var _binding: FragmentIssuesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: IssuesViewModel
    private lateinit var issueAdapter: IssuesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIssuesBinding.inflate(inflater, container, false)
        binding.toolbar.root.title = resources.getString(R.string.issues)
        issueAdapter = context?.let { IssuesAdapter(it) }!!
        binding.progressBar.visibility = View.VISIBLE
        binding.rvIssues.apply {
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL, false)
            adapter = issueAdapter
        }
        viewModel = ViewModelProvider(this, IssueViewModelFactory(DataRepository(ServiceHandler.apiService)))[IssuesViewModel::class.java]
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            withContext(Dispatchers.IO) {
                viewModel.getIssueLink()
            }
        }
        viewModel.observeIssueLiveData().observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            issueAdapter.setUpIssuesList(it)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}