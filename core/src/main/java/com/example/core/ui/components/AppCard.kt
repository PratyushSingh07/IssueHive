package com.example.core.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun AppCard(
    modifier: Modifier,
    shapes: Shape = RoundedCornerShape(12.dp),
    elevation: CardElevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp
    ),
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        modifier = modifier,
        shape = shapes,
        elevation = elevation,
        content = content
    )

}