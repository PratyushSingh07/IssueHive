package com.example.findissues.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.findissues.R
import com.example.findissues.api.ServiceHandler
import com.example.findissues.databinding.FragmentHomeBinding
import com.example.findissues.repository.UserRepository
import com.example.findissues.utils.Constants.FOLLOWERS
import com.example.findissues.utils.Constants.FOLLOWING
import com.example.findissues.utils.Constants.TWITTER_BASE_URL
import com.example.findissues.viewmodels.UserViewModel
import com.example.findissues.viewmodels.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.toolbar.root.title = resources.getString(R.string.home)
        viewModel = ViewModelProvider(
            this,
            UserViewModelFactory(UserRepository(ServiceHandler.apiService))
        )[UserViewModel::class.java]
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getUserDetail()
            }
        }
        viewModel.observeIssueLiveData().observe(viewLifecycleOwner, Observer {
            binding.name.text = it.name
            binding.githubUsername.text = it.login
            Glide.with(requireContext())
                .load(it.avatar_url)
                .into(binding.profileImage)
            binding.bio.text = it.bio.replace("\n", "")
            binding.tvCompany.text = it.company
            binding.tvLocation.text = it.location
            binding.tvTwitter.text = it.twitter_username
            binding.tvTwitter.setOnClickListener {
                val browserIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(goToTwitter()))
                context?.startActivity(browserIntent)
            }
            binding.tvFollowers.text = it.followers.toString() + " " + FOLLOWERS
            binding.tvFollowing.text = it.following.toString() + " " + FOLLOWING
        })
        return binding.root

    }

    private fun goToTwitter(): String {
        return TWITTER_BASE_URL + binding.tvTwitter.text.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}