package com.example.findissues.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.databinding.FragmentFollowersBinding
import com.example.findissues.ui.adapters.FollowersAdapter
import com.example.findissues.viewmodels.FollowersViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var followersViewModel: FollowersViewModel

    @Inject
    lateinit var followersAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = followersAdapter
        }
        followersViewModel = ViewModelProvider(this)[FollowersViewModel::class.java]

        followersViewModel.getFollowers()

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