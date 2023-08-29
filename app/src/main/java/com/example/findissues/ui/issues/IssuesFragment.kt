package com.example.findissues.ui.issues

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
import com.example.findissues.models.issues.IssuesList
import com.example.findissues.ui.IssuesScreen
import com.example.findissues.utils.IssuesUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IssuesFragment : Fragment() {

    private lateinit var viewModel: IssuesViewModel
    private var listState by mutableStateOf(emptyList<IssuesList>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[IssuesViewModel::class.java]
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            setContent {
                IssuesScreen(listState)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getIssueLink()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeIssueLiveData().collect {
                    when (it) {
                        is IssuesUiState.Loading -> {
//                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is IssuesUiState.ListIssues -> {
//                            binding.progressBar.visibility = View.GONE
                            listState = it.issuesList
                        }
                    }
                }
            }
        }
    }
}