package com.javi.testproject.domain

import com.javi.testproject.data.LoginDataSource
import com.javi.testproject.data.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource
): LoginRepository {

    override fun login(username: String, password: String): Flow<Unit> {
        return loginDataSource.login(username, password)
    }

    override fun loginWithToken(token: String): Flow<Unit> {
        return loginDataSource.loginWithToken(token)
    }
}