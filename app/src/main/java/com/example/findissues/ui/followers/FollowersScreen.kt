package com.example.findissues.ui.followers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core.ui.components.AppCard
import com.example.findissues.models.home.Followers

@Composable
fun FollowersScreen(list: List<Followers>) {

    LazyColumn(
        modifier = Modifier.background(Color(0xFF0d1117))
    ) {
        items(list) { item ->
            AppCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color(0xFF0d1117))
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = item.avatar_url,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(24.dp))

                    Text(
                        text = item.login,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun FollowersPreviewScreen() {
    FollowersScreen(emptyList())
}