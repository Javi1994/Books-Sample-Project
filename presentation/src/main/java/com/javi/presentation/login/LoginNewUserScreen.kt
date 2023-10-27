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
import com.javi.presentation.destinations.HomeScreenDestination
import com.javi.presentation.destinations.LoginSavedUserScreenDestination
import com.javi.presentation.login.viewmodel.LoginUiEvent
import com.javi.presentation.login.viewmodel.LoginUiState
import com.javi.presentation.login.viewmodel.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = true)
fun LoginNewUserScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = koinViewModel()
) {
    if (viewModel.state.loginSuccess) {
        navigator.navigate(HomeScreenDestination)
    }

    if (!viewModel.state.userFromPreferences.isNullOrEmpty()) {
        navigator.navigate(LoginSavedUserScreenDestination)
    }

    LoginNewUserLayout(
        state = viewModel.state,
        onUpdateUsername = {
            viewModel.onEvent(LoginUiEvent.UpdateUsername(it))
        },
        onUpdatePassword = {
            viewModel.onEvent(LoginUiEvent.UpdatePassword(it))
        },
        onLogin = {
            viewModel.onEvent(LoginUiEvent.LoginWithUsername)
        }
    )
}

@Composable
private fun LoginNewUserLayout(
    state: LoginUiState,
    onUpdateUsername: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onLogin: () -> Unit
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
            onLogin()
        }
    }
}

@Preview
@Composable
private fun LoginNewUserScreenPreview() {
    LoginNewUserLayout(state = LoginUiState(), {}, {}, {})
}