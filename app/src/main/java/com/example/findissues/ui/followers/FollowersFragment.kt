package com.example.findissues.ui.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.findissues.models.home.Followers
import com.example.findissues.utils.FollowersUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private lateinit var followersViewModel: FollowersViewModel

    private var listState by mutableStateOf(emptyList<Followers>())
    private var isLoading by mutableStateOf(false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        followersViewModel = ViewModelProvider(this)[FollowersViewModel::class.java]

        followersViewModel.getFollowers()

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            setContent {
                FollowersScreen(listState, isLoading)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                followersViewModel.observeFollowersLiveData().collect {
                    when (it) {
                        is FollowersUiState.FollowersList -> {
                            isLoading = false
                            listState = it.followersList
                        }
                        is FollowersUiState.Loading -> {
                            isLoading = true
                        }
                    }
                }
            }
        }
    }

}