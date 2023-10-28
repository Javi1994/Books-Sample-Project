package com.javi.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider() {
    Divider(
        color = Color.DarkGray,
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
    )
}

@Preview
@Composable
private fun VerticalDividerPreview() {
    VerticalDivider()
}