package com.javi.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun EmptyDataItem(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = message,
            fontSize = 24.sp,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun EmptyDataPreview() {
    EmptyDataItem("No books data")
}