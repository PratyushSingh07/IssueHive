package com.example.findissues.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.databinding.FragmentIssuesBinding
import com.example.findissues.ui.adapters.IssuesAdapter
import com.example.findissues.viewmodels.IssuesViewModel

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
        issueAdapter = context?.let { IssuesAdapter(it) }!!
        binding.rvIssues.apply {
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL, false)
            adapter = issueAdapter
        }
        viewModel = ViewModelProvider(this)[IssuesViewModel::class.java]
        viewModel.getIssueLink()
        viewModel.observeIssueLiveData().observe(viewLifecycleOwner, Observer {
            issueAdapter.setUpIssuesList(it)
        })
        return binding.root
    }

}