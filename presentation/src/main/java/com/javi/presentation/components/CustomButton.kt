package com.javi.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    value: String,
    isEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = {
            if (!isLoading) {
                onClick()
            }
        },
        enabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        } else {
            Text(text = value)
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(value = "Login", isEnabled = true, isLoading = true) {

    }
}