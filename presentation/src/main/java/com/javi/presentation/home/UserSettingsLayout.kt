package com.javi.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javi.domain.model.User
import com.javi.presentation.R
import com.javi.presentation.components.CustomButton
import com.javi.presentation.components.CustomErrorItem
import com.javi.presentation.components.CustomLoaderItem

@Composable
fun UserSettingsLayout(
    user: User?,
    isLoading: Boolean = false,
    error: Exception? = null,
    isLogoutLoading: Boolean = false,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        CustomLoaderItem(modifier = modifier)
    } else if (error != null) {
        CustomErrorItem(modifier = modifier)
    } else {
        UserSettingsData(
            user = user,
            isLogoutLoading = isLogoutLoading,
            onLogoutClick = { onLogoutClick() },
            modifier = modifier
        )
    }
}

@Composable
private fun UserSettingsData(
    user: User?,
    isLogoutLoading: Boolean,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    user?.let {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = it.username,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.email,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray
                )
            }
            CustomButton(
                value = stringResource(id = R.string.home_user_detail_logout),
                isEnabled = true,
                isLoading = isLogoutLoading,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(80.dp),
            ) {
                onLogoutClick()
            }
        }
    }
}


@Preview
@Composable
private fun UserSettingsLayoutPreview() {
    UserSettingsLayout(
        user = User(),
        onLogoutClick = {},
    )
}