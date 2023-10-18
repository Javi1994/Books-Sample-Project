package com.javi.testproject.data.repository

import com.javi.testproject.data.datasource.remote.LoginApi
import com.javi.testproject.data.dto.UserDto
import com.javi.testproject.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
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