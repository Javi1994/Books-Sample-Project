package com.javi.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.common.ValidatePassword
import com.javi.common.ValidateUsername
import com.javi.domain.model.User
import com.javi.domain.use_case.login.LoginUseCase
import com.javi.domain.use_case.login.LogoutUseCase
import com.javi.domain.use_case.preferences.GetUserFromPreferencesUseCase
import com.javi.presentation.login.LoginUiEvent
import com.javi.presentation.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserFromPreferencesUseCase: GetUserFromPreferencesUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = getUserFromPreferencesUseCase.invoke().first()
            _uiState.update {
                it.copy(userFromPreferences = user)
            }
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

            is LoginUiEvent.Logout -> {
                logout()
            }
        }
    }

    private fun updateUsername(event: LoginUiEvent.UpdateUsername) {
        val validationResult = ValidateUsername.execute(event.username).errorMessage
        _uiState.update {
            it.copy(
                username = event.username,
                usernameError = validationResult
            )
        }
    }

    private fun updatePassword(event: LoginUiEvent.UpdatePassword) {
        val validationResult = ValidatePassword.execute(event.password).errorMessage
        _uiState.update {
            it.copy(
                password = event.password,
                passwordError = validationResult
            )
        }
    }

    private fun login() {
        viewModelScope.launch {
            loginUseCase
                .invoke(
                    username = uiState.value.username,
                    password = uiState.value.password
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
                    username = uiState.value.userFromPreferences?.username ?: "",
                    password = uiState.value.password
                ).collect { result ->
                    updateUiState(result)
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase
                .invoke()
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            userFromPreferences = null,
                            isLoadingLogout = result.isLoading,
                            requestError = result.hasError,
                            username = "",
                            usernameError = null,
                            password = "",
                            passwordError = null
                        )
                    }
                }
        }
    }

    private fun updateUiState(result: Resource<User>) {
        when (result) {
            is Resource.Success -> {
                result.data?.let { user ->
                    _uiState.update {
                        it.copy(
                            userFromLogin = user,
                            isLoadingLogin = result.isLoading,
                            requestError = result.hasError
                        )
                    }
                }
            }

            is Resource.Loading -> {
                _uiState.update {
                    it.copy(
                        isLoadingLogin = result.isLoading,
                        requestError = result.hasError
                    )
                }
            }

            is Resource.Error -> {
                _uiState.update {
                    it.copy(
                        isLoadingLogin = result.isLoading,
                        requestError = result.hasError
                    )
                }
            }
        }
    }
}