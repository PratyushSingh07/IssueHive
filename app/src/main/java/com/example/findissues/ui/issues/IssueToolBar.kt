package com.example.findissues.ui.issues

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun IssueToolBar() {
    TopAppBar(
        title = {
            Text(
                text = "Issues",
                fontWeight = FontWeight.Medium,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        backgroundColor = Color(0xFF0d1117),
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}