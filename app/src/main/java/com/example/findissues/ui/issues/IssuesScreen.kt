package com.example.findissues.ui.issues

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.ui.components.AppCard
import com.example.core.ui.components.AppCircularProgressBar
import com.example.core.ui.components.AppRowCard
import com.example.core.ui.components.Tag
import com.example.findissues.R
import com.example.findissues.models.issues.IssuesList
import com.example.findissues.utils.Browser

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IssuesScreen(
    list: List<IssuesList>, isLoading: Boolean, viewModel: IssuesViewModel
) {
    val context = LocalContext.current

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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
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
                            text = stringResource(R.string.please_select_a_tag),
                            color = Color.White,
                            modifier = Modifier,
                            style = MaterialTheme.typography.headlineLarge
                        )

                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.background(Color(0xFF0d1117)),
                        state = rememberLazyListState()
                    ) {
                        items(list) { items ->
                            AppCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable(onClick = {
                                        Browser(context).launch(items.html_url)
                                    })
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