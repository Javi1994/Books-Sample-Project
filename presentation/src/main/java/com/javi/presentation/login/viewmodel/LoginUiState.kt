package com.javi.presentation.login.viewmodel

data class LoginUiState(
    val username: String = "",
    val usernameError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val userFromPreferences: String? = null,
    val loginSuccess: Boolean = false,
    val isLoadingLogin: Boolean = false,
    val isLoadingLogout: Boolean = false,
    val error: Exception? = null
) {
    val canEnableLoginButton: Boolean
        get() = usernameError == null && username.isNotEmpty()
                && passwordError == null && password.isNotEmpty()
    val canEnableLoginButtonFromPassword: Boolean
        get() = passwordError == null && password.isNotEmpty() &&
                userFromPreferences?.isNotEmpty() ?: false
}

sealed class LoginUiEvent {
    object LoginWithUsername : LoginUiEvent()
    object LoginWithPassword : LoginUiEvent()
    data class UpdateUsername(val username: String) : LoginUiEvent()
    data class UpdatePassword(val password: String) : LoginUiEvent()
}