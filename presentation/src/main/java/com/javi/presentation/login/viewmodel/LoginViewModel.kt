package com.javi.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.domain.model.User
import com.javi.domain.use_case.login.LoginUseCase
import com.javi.domain.use_case.preferences.GetUserFromPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserFromPreferencesUseCase: GetUserFromPreferencesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getUserFromPreferencesUseCase.invoke()
                .collect { user ->
                    _uiState.update {
                        it.copy(userFromPreferences = user)
                    }
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
                _uiState.update {
                    it.copy(username = event.username)
                }
            }

            is LoginUiEvent.UpdatePassword -> {
                _uiState.update {
                    it.copy(password = event.password)
                }
            }

            is LoginUiEvent.Logout -> {

            }
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

    }

    private fun updateUiState(result: Resource<User>) {
        when (result) {
            is Resource.Success -> {
                result.data?.let { user ->
                    _uiState.update {
                        it.copy(
                            userFromLogin = user,
                            isLoading = result.isLoading,
                            error = result.hasError
                        )
                    }
                }
            }

            is Resource.Loading -> {
                _uiState.update {
                    it.copy(
                        isLoading = result.isLoading,
                        error = result.hasError
                    )
                }
            }

            is Resource.Error -> {
                _uiState.update {
                    it.copy(
                        isLoading = result.isLoading,
                        error = result.hasError
                    )
                }
            }
        }
    }
}