package com.example.findissues.ui.issues

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.ui.components.AppCard
import com.example.core.ui.components.AppCircularProgressBar
import com.example.core.ui.components.AppRowCard
import com.example.core.ui.components.Tag
import com.example.findissues.models.issues.IssuesList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IssuesScreen(
    list: List<IssuesList>, isLoading: Boolean, viewModel: IssuesViewModel
) {
    Scaffold(topBar = { IssueToolBar() }) {
        if (isLoading) {
            AppCircularProgressBar()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0d1117))
                    .padding(it)
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()

                ) {
                    viewModel.getTags().forEach { tag ->
                        Tag(tagText = tag, selected = tag == viewModel.tag) {
                            viewModel.tagSelected(tag)
                        }
                    }
                }
                if (list.isEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Please select a valid tag",
                            color = Color.White,
                            modifier = Modifier,
                            style = MaterialTheme.typography.headlineLarge
                        )

                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.background(Color(0xFF0d1117))
                    ) {
                        items(list) { items ->
                            AppCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                AppRowCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF0d1117))
                                        .padding(16.dp), text = items.url
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun IssuesScreenPreview() {
    IssuesScreen(list = emptyList(), isLoading = false, viewModel = viewModel())
}