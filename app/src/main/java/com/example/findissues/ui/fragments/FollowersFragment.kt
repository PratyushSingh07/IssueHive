package com.example.findissues.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.databinding.FragmentFollowersBinding
import com.example.findissues.ui.adapters.FollowersAdapter
import com.example.findissues.viewmodels.FollowersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var followersAdapter: FollowersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        followersAdapter = context?.let { FollowersAdapter(it) }!!
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = followersAdapter
        }
        followersViewModel = ViewModelProvider(
            this
        )[FollowersViewModel::class.java]


        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                followersViewModel.getFollowers()
            }
        }

        followersViewModel.observeFollowersLiveData().observe(viewLifecycleOwner) {
            followersAdapter.setUpFollowersList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}