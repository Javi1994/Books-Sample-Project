package com.javi.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.domain.model.User

@Composable
fun UserSettingsLayout(user: User, isLoading: Boolean = false) {
    if (!isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 65.dp)
        ) {
            Text(text = user.username)
            Text(text = user.email)
        }
    } else {
        CircularProgressIndicator()
    }
}


@Preview
@Composable
private fun UserSettingsLayoutPreview() {
    UserSettingsLayout(User())
}