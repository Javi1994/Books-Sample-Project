package com.javi.data.repository.impl

import com.javi.data.datasource.LoginDataSource
import com.javi.data.dto.UserDto
import com.javi.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    override fun login(username: String, password: String): Flow<UserDto> {
        return loginDataSource.login(username, password)
            .onEach {
                println("Login result: $it")
            }
    }

    override fun loginWithToken(token: String): Flow<UserDto> {
        return loginDataSource.loginWithToken(token)
            .onEach {
                println("Login result: $it")
            }
    }

    override fun logout(): Flow<Unit> {
        return loginDataSource.logout()
    }
}