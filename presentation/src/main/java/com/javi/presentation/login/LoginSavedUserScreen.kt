package com.javi.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javi.presentation.R
import com.javi.presentation.components.CustomButton
import com.javi.presentation.components.CustomTextField
import com.javi.presentation.destinations.HomeScreenDestination
import com.javi.presentation.login.viewmodel.LoginUiEvent
import com.javi.presentation.login.viewmodel.LoginUiState
import com.javi.presentation.login.viewmodel.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun LoginSavedUserScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = koinViewModel()
) {
    if (viewModel.state.loginSuccess) {
        navigator.navigate(HomeScreenDestination)
    }

    LoginSavedUserLayout(
        state = viewModel.state,
        onUpdatePassword = {
            viewModel.onEvent(LoginUiEvent.UpdatePassword(it))
        },
        onLoginWithPassword = {
            viewModel.onEvent(LoginUiEvent.LoginWithPassword)
        }
    )
}

@Composable
private fun LoginSavedUserLayout(
    state: LoginUiState,
    onUpdatePassword: (String) -> Unit,
    onLoginWithPassword: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(80.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.login_welcome_back,
                state.userFromPreferences ?: ""
            ),
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = state.password,
            errorResource = state.passwordError
        ) {
            onUpdatePassword(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            value = stringResource(R.string.login_do_login_btn),
            isEnabled = state.canEnableLoginButtonFromPassword,
            isLoading = state.isLoadingLogin
        ) {
            onLoginWithPassword()
        }
    }
}

@Preview
@Composable
private fun LoginNewUserScreenPreview() {
    LoginSavedUserLayout(
        state = LoginUiState(userFromPreferences = "Javi"), {}, {})
}