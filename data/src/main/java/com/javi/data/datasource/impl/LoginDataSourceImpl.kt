package com.javi.data.datasource.impl

import com.javi.data.datasource.LoginDataSource
import com.javi.data.datasource.database.BookDao
import com.javi.data.datasource.local.UserPreferences
import com.javi.data.datasource.remote.LoginApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val userPreferences: UserPreferences,
    private val bookDatabase: BookDao
) : LoginDataSource {
    override fun login(username: String, password: String): Flow<UserDto> {
        return loginApi.doLogin(username, password)
            .onEach {
                userPreferences.saveUser(
                    it.copy(username = username)
                )
            }
    }

    override fun loginWithToken(token: String): Flow<UserDto> {
        return loginApi.doLoginWithToken(token)
    }

    override fun logout(): Flow<Unit> {
        return loginApi.doLogout()
            .onEach {
                userPreferences.clearPreferences()

                withContext(Dispatchers.IO) {
                    bookDatabase.deleteAll()
                }
            }
    }
}