package com.javi.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.domain.model.User
import com.javi.domain.use_case.login.LoginUseCase
import com.javi.domain.use_case.preferences.GetUserFromPreferencesUseCase
import com.javi.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserFromPreferencesUseCase: GetUserFromPreferencesUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    var user: MutableStateFlow<User?> = MutableStateFlow(null)

    init {
        getUserFromPreferencesUseCase.invoke()
            .onEach {
                user.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun doLogin() {
        loginUseCase.invoke("username", "password")
            .map {
                val uiState = UiState.Success(it)
                println("Mapping login result to uiState: $uiState")
                uiState
            }
            .onEach {
                println("Emitting uiState: $it")
                uiState.emit(it)
            }
            .launchIn(viewModelScope)
    }
}