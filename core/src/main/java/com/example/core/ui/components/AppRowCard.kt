package com.example.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun AppRowCard(
    modifier: Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    imageUrl: String? = null,
    contentDescription: String? = null,
    text: String
) {
    Row(
        modifier = modifier, verticalAlignment = verticalAlignment
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
        }
        Text(
            text = text, textAlign = TextAlign.Center, color = Color.White, fontSize = 16.sp
        )
    }
}