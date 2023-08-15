package com.example.findissues.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findissues.R
import com.example.findissues.databinding.FragmentHomeBinding
import com.example.findissues.ui.adapters.PinnedRepoAdapter
import com.example.findissues.utils.Browser
import com.example.findissues.utils.Constants.FOLLOWERS
import com.example.findissues.utils.Constants.FOLLOWING
import com.example.findissues.utils.Constants.TWITTER_BASE_URL
import com.example.findissues.utils.GlideLoader
import com.example.findissues.viewmodels.PinnedRepoViewModel
import com.example.findissues.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel
    private lateinit var pinnedRepoViewModel: PinnedRepoViewModel

    @Inject
    lateinit var pinnedRepoAdapter: PinnedRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.toolbar.root.title = resources.getString(R.string.home)
        binding.rvPinned.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = pinnedRepoAdapter
        }
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        pinnedRepoViewModel = ViewModelProvider(this)[PinnedRepoViewModel::class.java]

        userViewModel.getUserDetail()
        userViewModel.observeUserLiveData().observe(viewLifecycleOwner) {
            with(binding) {
                name.text = it.name
                githubUsername.text = it.login
                val glideLoader = GlideLoader(requireContext())
                glideLoader.loadCircularImage(it.avatar_url, profileImage)
                bio.text = it.bio.replace("\n", "")
                tvCompany.text = it.company
                tvLocation.text = it.location
                tvTwitter.text = it.twitter_username
                tvTwitter.setOnClickListener {
                    Browser(requireContext()).launch(goToTwitter())
                }
                tvFollowers.text = it.followers.toString() + " " + FOLLOWERS
                tvFollowing.text = it.following.toString() + " " + FOLLOWING
            }
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                pinnedRepoViewModel.getPinnedRepos()
            }
        }

        pinnedRepoViewModel.observePinnedRepoLiveData().observe(viewLifecycleOwner) {
            pinnedRepoAdapter.setUpPinnedRepoList(it)
        }

        with(binding) {
            tvFollowers.setOnClickListener {
                setUpFragment(FollowersFragment())
            }
            tvFollowing.setOnClickListener {
                setUpFragment(FollowingFragment())
            }
            tvRepositories.setOnClickListener {
                setUpFragment(RepositoryFragment())
            }
            tvStarred.setOnClickListener {
                setUpFragment(StarredFragment())
            }
        }

        return binding.root
    }

    private fun goToTwitter(): String {
        return TWITTER_BASE_URL + binding.tvTwitter.text.toString()
    }

    private fun setUpFragment(fragment: Fragment) {
        val fragment = fragment
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_dashboard, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}