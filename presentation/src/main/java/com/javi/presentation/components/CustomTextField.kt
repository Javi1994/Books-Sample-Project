package com.javi.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    errorResource: Int?,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        supportingText = {
            if (errorResource != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(errorResource),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            if (errorResource != null) {
                Icon(Icons.Filled.Clear, "error", tint = MaterialTheme.colorScheme.error)
            }
        },
        isError = errorResource != null
    )
}