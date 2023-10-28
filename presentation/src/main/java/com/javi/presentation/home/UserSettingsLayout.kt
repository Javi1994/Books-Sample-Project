package com.javi.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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

@Composable
fun UserSettingsLayout(
    user: User?,
    isLoading: Boolean = false,
    isLogoutLoading: Boolean = false,
    onLogoutClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 65.dp)
    ) {
        if (!isLoading) {
            user?.let {
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
        } else {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


@Preview
@Composable
private fun UserSettingsLayoutPreview() {
    UserSettingsLayout(User()) {}
}