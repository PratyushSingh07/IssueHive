package com.example.findissues.ui.following

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.components.AppCard
import com.example.core.ui.components.AppRowCard
import com.example.findissues.models.home.Following

@Composable
fun FollowingScreen(list: List<Following>) {

    LazyColumn(
        modifier = Modifier.background(Color(0xFF0d1117))
    ) {
        items(list) { item ->
            AppCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                AppRowCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0d1117))
                        .padding(16.dp),
                    imageUrl = item.avatar_url,
                    text = item.login
                )
            }
        }
    }
}

@Preview
@Composable
fun FollowingScreenPreview() {
    FollowingScreen(emptyList())
}