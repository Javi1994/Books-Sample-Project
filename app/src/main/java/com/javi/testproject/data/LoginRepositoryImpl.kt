package com.javi.testproject.data

import com.javi.testproject.data.remote.LoginApi
import com.javi.testproject.data.dto.UserDto
import com.javi.testproject.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class LoginRepositoryImpl(
    private val loginApi: LoginApi
) : LoginRepository {

    override fun login(username: String, password: String): Flow<UserDto> {
        return loginApi.doLogin(username, password)
            .onEach {
                println("Login result: $it")
            }
    }

    override fun loginWithToken(token: String): Flow<UserDto> {
        return loginApi.doLoginWithToken(token)
            .onEach {
                println("Login result: $it")
            }
    }
}