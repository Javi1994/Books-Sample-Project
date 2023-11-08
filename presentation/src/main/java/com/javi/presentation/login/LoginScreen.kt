package com.javi.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.javi.presentation.ObserveAsEvents
import com.javi.presentation.destinations.HomeScreenDestination
import com.javi.presentation.login.viewmodel.LoginNavigationEvent
import com.javi.presentation.login.viewmodel.LoginUiEvent
import com.javi.presentation.login.viewmodel.LoginUiState
import com.javi.presentation.login.viewmodel.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = true)
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = koinViewModel()
) {

    ObserveAsEvents(viewModel.navigationEventsChannelFlow) { event ->
        when (event) {
            is LoginNavigationEvent.NavigateToHome -> {
                navigator.navigate(HomeScreenDestination)
            }
        }
    }

    LoginLayout(
        state = viewModel.state,
        onUpdateUsername = {
            viewModel.onEvent(LoginUiEvent.UpdateUsername(it))
        },
        onUpdatePassword = {
            viewModel.onEvent(LoginUiEvent.UpdatePassword(it))
        },
        onLoginWithUsername = {
            viewModel.onEvent(LoginUiEvent.LoginWithUsername)
        },
        onLoginWithPassword = {
            viewModel.onEvent(LoginUiEvent.LoginWithPassword)
        }
    )
}

@Composable
private fun LoginLayout(
    state: LoginUiState,
    onUpdateUsername: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onLoginWithUsername: () -> Unit,
    onLoginWithPassword: () -> Unit
) {
    if (state.userFromPreferences != null) {
        LoginSavedUserLayout(
            state = state,
            onUpdatePassword = {
                onUpdatePassword(it)
            },
            onLoginWithPassword = {
                onLoginWithPassword()
            }
        )
    } else {
        LoginNewUserLayout(
            state = state,
            onUpdatePassword = {
                onUpdatePassword(it)
            },
            onUpdateUsername = {
                onUpdateUsername(it)
            },
            onLoginWithUsername = {
                onLoginWithUsername()
            }
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginLayout(state = LoginUiState(), {}, {}, {}, {})
}