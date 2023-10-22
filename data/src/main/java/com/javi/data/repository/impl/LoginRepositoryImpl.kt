package com.javi.data.repository.impl

import com.javi.common.Resource
import com.javi.data.datasource.LoginDataSource
import com.javi.data.dto.UserDto
import com.javi.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    override fun login(username: String, password: String): Flow<Resource<UserDto>> {
        return loginDataSource.login(username, password)
    }

    override fun loginWithToken(token: String): Flow<Resource<UserDto>> {
        return loginDataSource.loginWithToken(token)
    }

    override fun logout(): Flow<Resource<Unit>> {
        return loginDataSource.logout()
    }
}