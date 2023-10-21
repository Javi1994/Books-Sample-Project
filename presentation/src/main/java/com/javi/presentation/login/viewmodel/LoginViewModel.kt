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

    private var _user: User? = null

    init {
        getUserFromPreferencesUseCase.invoke()
            .onEach {
                _user = it
                user.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun doLogin(username: String, password: String) {
        loginUseCase.invoke(username, password)
            .map {
                UiState.Success(it)
            }
            .onEach {
                uiState.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun doLogin(password: String) {
        loginUseCase.invoke(_user?.username!!, password)
            .map {
                UiState.Success(it)
            }
            .onEach {
                uiState.emit(it)
            }
            .launchIn(viewModelScope)
    }
}