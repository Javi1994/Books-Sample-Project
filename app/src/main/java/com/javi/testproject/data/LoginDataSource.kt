package com.javi.testproject.data

import com.javi.testproject.data.remote.LoginApi
import kotlinx.coroutines.flow.Flow

class LoginDataSource(private val loginApi: LoginApi) {

    fun login(username: String, password: String): Flow<Unit> {
        return loginApi.doLogin(username, password)
    }

    fun loginWithToken(token: String): Flow<Unit> {
        return loginApi.doLoginWithToken(token)
    }
}