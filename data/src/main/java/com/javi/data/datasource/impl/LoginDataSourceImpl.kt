package com.javi.data.datasource.impl

import com.javi.common.Resource
import com.javi.data.datasource.LoginDataSource
import com.javi.data.datasource.database.BookDao
import com.javi.data.datasource.local.UserPreferences
import com.javi.data.datasource.remote.LoginApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException

class LoginDataSourceImpl constructor(
    private val loginApi: LoginApi,
    private val userPreferences: UserPreferences,
    private val bookDatabase: BookDao,
    private val ioDispatcher: CoroutineDispatcher
) : LoginDataSource {
    override suspend fun login(username: String, password: String): Flow<Resource<UserDto>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val loginResult = loginApi.doLogin(username, password)
                delay(2000)
                emit(Resource.Success(loginResult))

                userPreferences.saveUser(
                    loginResult.copy(username = username)
                )

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun loginWithToken(token: String): Flow<Resource<UserDto>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val loginResult = loginApi.doLoginWithToken(token)
                delay(2000)
                emit(Resource.Success(loginResult))

                userPreferences.saveUser(loginResult)

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e))

            }
        }
    }

    override suspend fun logout(): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                loginApi.doLogout()
                delay(2000)

                userPreferences.clearPreferences()

                withContext(ioDispatcher) {
                    bookDatabase.deleteAll()
                }
                emit(Resource.Success(Unit))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e))
            }
        }
    }
}