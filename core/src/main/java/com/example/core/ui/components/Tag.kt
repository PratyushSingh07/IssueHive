package com.example.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Tag(tagText: String, selected: Boolean, onclick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable(enabled = true, "select the tag", Role.Button, onClick = onclick)
            .clip(RoundedCornerShape(100.dp))
            .border(
                BorderStroke(1.dp, Color(0xFF018786)), shape = RoundedCornerShape(100.dp)
            )
            .background(if (selected) Color(0xFF018786) else Color(0xFF0d1117))
            .padding(10.dp)
    ) {
        Text(
            text = tagText,
            color = if (selected) Color(0xFF0d1117) else Color(0xFF018786),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}