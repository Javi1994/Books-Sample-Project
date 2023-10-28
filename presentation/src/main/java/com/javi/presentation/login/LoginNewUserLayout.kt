package com.javi.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.presentation.R
import com.javi.presentation.components.CustomButton
import com.javi.presentation.components.CustomTextField
import com.javi.presentation.login.viewmodel.LoginUiState

@Composable
fun LoginNewUserLayout(
    state: LoginUiState,
    onUpdateUsername: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onLoginWithUsername: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(80.dp)
    ) {
        CustomTextField(
            value = state.username,
            errorResource = state.usernameError
        ) {
            onUpdateUsername(it)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = state.password,
            errorResource = state.passwordError
        ) {
            onUpdatePassword(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            value = stringResource(R.string.login_do_login_btn),
            isEnabled = state.canEnableLoginButton,
            isLoading = state.isLoadingLogin
        ) {
            onLoginWithUsername()
        }
    }
}

@Preview
@Composable
private fun LoginNewUserScreenPreview() {
    LoginNewUserLayout(state = LoginUiState(), {}, {}, {})
}