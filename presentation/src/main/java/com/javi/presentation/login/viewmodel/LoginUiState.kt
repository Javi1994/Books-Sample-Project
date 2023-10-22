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

sealed class LoginEvent {
    object Logout : LoginEvent()
    object LoginWithUsername : LoginEvent()
    object LoginWithPassword : LoginEvent()
    data class UpdateUsername(val username: String) : LoginEvent()
    data class UpdatePassword(val password: String) : LoginEvent()
}