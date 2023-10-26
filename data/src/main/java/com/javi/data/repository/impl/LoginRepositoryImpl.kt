package com.javi.data.repository.impl

import com.javi.common.Resource
import com.javi.data.datasource.LoginDataSource
import com.javi.data.dto.UserDto
import com.javi.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    override suspend fun login(username: String, password: String): Flow<Resource<UserDto>> {
        return loginDataSource.login(username, password)
    }

    override suspend fun loginWithToken(token: String): Flow<Resource<UserDto>> {
        return loginDataSource.loginWithToken(token)
    }

    override suspend fun logout(): Flow<Resource<Unit>> {
        return loginDataSource.logout()
    }
}