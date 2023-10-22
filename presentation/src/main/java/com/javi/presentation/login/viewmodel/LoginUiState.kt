package com.javi.presentation.login.viewmodel

import com.javi.domain.model.User

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val userFromPreferences: User? = null,
    val userFromLogin: User? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
) {
    val hasUserDataFromPreferences: Boolean
        get() = userFromPreferences != null
    val canEnableLoginButton: Boolean
        get() = password.isNotEmpty() && username.isNotEmpty()
    val canEnableLoginButtonFromPassword: Boolean
        get() = password.isNotEmpty() &&
                userFromPreferences?.username?.isNotEmpty() ?: false
    val loginSuccess: Boolean
        get() = userFromLogin != null && !isLoading && !error
}

sealed class LoginUiEvent {
    object Logout : LoginUiEvent()
    object LoginWithUsername : LoginUiEvent()
    object LoginWithPassword : LoginUiEvent()
    data class UpdateUsername(val username: String) : LoginUiEvent()
    data class UpdatePassword(val password: String) : LoginUiEvent()
}