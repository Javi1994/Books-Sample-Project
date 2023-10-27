package com.javi.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.presentation.R
import com.javi.presentation.components.CustomButton
import com.javi.presentation.components.CustomTextField
import com.javi.presentation.login.viewmodel.LoginUiEvent
import com.javi.presentation.login.viewmodel.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(start = true)
fun LoginNewUserScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.state
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
            viewModel.onEvent(LoginUiEvent.UpdateUsername(it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = state.password,
            errorResource = state.passwordError
        ) {
            viewModel.onEvent(LoginUiEvent.UpdatePassword(it))
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            value = stringResource(R.string.login_do_login_btn),
            isEnabled = state.canEnableLoginButton,
            isLoading = state.isLoadingLogin
        ) {
            viewModel.onEvent(LoginUiEvent.LoginWithUsername)
        }
    }
}

@Preview
@Composable
private fun LoginNewUserScreenPreview() {
}