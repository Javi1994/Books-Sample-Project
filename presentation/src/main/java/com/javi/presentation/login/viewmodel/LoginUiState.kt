package com.javi.presentation.login.viewmodel

import com.javi.domain.model.User

data class LoginUiState(
    val username: String = "",
    val usernameError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val userFromPreferences: User? = null,
    val userFromLogin: User? = null,
    val isLoadingLogin: Boolean = false,
    val isLoadingLogout: Boolean = false,
    val requestError: Exception? = null
) {
    val canEnableLoginButton: Boolean
        get() = usernameError == null && username.isNotEmpty()
                && passwordError == null && password.isNotEmpty()
    val canEnableLoginButtonFromPassword: Boolean
        get() = passwordError == null && password.isNotEmpty() &&
                userFromPreferences?.username?.isNotEmpty() ?: false
    val loginSuccess: Boolean
        get() = userFromLogin != null && !isLoadingLogin && requestError == null
}

sealed class LoginUiEvent {
    object LoginWithUsername : LoginUiEvent()
    object LoginWithPassword : LoginUiEvent()
    data class UpdateUsername(val username: String) : LoginUiEvent()
    data class UpdatePassword(val password: String) : LoginUiEvent()
}