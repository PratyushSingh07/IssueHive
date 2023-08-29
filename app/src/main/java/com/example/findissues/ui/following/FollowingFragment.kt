package com.example.findissues.ui.following

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
import com.example.findissues.models.home.Following
import com.example.findissues.utils.FollowingUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowingFragment : Fragment() {

    private lateinit var followingViewModel: FollowingViewModel

    private var listState by mutableStateOf(emptyList<Following>())
    private var isLoading by mutableStateOf(false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        followingViewModel = ViewModelProvider(this)[FollowingViewModel::class.java]

        followingViewModel.getFollowing()

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            setContent {
                FollowingScreen(listState,isLoading)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                followingViewModel.observeFollowingList().collect {
                    when (it) {
                        is FollowingUiState.FollowingList -> {
                            isLoading = false
                            listState = it.followingList
                        }
                        is FollowingUiState.Loading -> {
                            isLoading = true
                        }
                    }
                }
            }
        }
    }
}