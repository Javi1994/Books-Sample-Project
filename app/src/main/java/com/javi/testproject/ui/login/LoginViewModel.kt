package com.javi.testproject.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.testproject.data.LoginDataSource
import com.javi.testproject.data.remote.LoginApi
import com.javi.testproject.domain.LoginRepositoryImpl
import com.javi.testproject.domain.use_case.LoginUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(
    private val loginUseCase: LoginUseCase = LoginUseCase(
        LoginRepositoryImpl(LoginDataSource())
    )
) : ViewModel() {

    fun doLogin(username: String, password: String) {
        loginUseCase.invoke(username, password)
            .onEach {

            }
            .launchIn(viewModelScope)
    }
}