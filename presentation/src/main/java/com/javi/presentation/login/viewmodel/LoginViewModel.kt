package com.javi.presentation.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.common.ValidatePassword
import com.javi.common.ValidateUsername
import com.javi.domain.model.User
import com.javi.domain.use_case.login.LoginUseCase
import com.javi.domain.use_case.preferences.GetUserFromPreferencesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserFromPreferencesUseCase: GetUserFromPreferencesUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginUiState())

    init {
        viewModelScope.launch {
            val user = getUserFromPreferencesUseCase.invoke().first()
            state = state.copy(userFromPreferences = user)
        }
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.LoginWithUsername -> {
                login()
            }

            is LoginUiEvent.LoginWithPassword -> {
                loginWithPassword()
            }

            is LoginUiEvent.UpdateUsername -> {
                updateUsername(event)
            }

            is LoginUiEvent.UpdatePassword -> {
                updatePassword(event)
            }
        }
    }

    private fun updateUsername(event: LoginUiEvent.UpdateUsername) {
        val validationResult = ValidateUsername.execute(event.username).errorMessage
        state = state.copy(
            username = event.username,
            usernameError = validationResult
        )
    }

    private fun updatePassword(event: LoginUiEvent.UpdatePassword) {
        val validationResult = ValidatePassword.execute(event.password).errorMessage
        state = state.copy(
            password = event.password,
            passwordError = validationResult
        )
    }

    private fun login() {
        viewModelScope.launch {
            loginUseCase
                .invoke(
                    username = state.username,
                    password = state.password
                )
                .collect { result ->
                    updateUiState(result)
                }
        }
    }

    private fun loginWithPassword() {
        viewModelScope.launch {
            loginUseCase
                .invoke(
                    username = state.userFromPreferences?.username ?: "",
                    password = state.password
                ).collect { result ->
                    updateUiState(result)
                }
        }
    }

    private fun updateUiState(result: Resource<User>) {
        when (result) {
            is Resource.Success -> {
                result.data?.let { user ->
                    state = state.copy(
                        userFromLogin = user,
                        isLoadingLogin = result.isLoading,
                        requestError = result.error
                    )
                }
            }

            is Resource.Loading -> {
                state = state.copy(
                    isLoadingLogin = result.isLoading,
                    requestError = result.error
                )
            }

            is Resource.Error -> {
                state = state.copy(
                    isLoadingLogin = result.isLoading,
                    requestError = result.error
                )
            }
        }
    }
}