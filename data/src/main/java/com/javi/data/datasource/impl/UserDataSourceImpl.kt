package com.javi.data.datasource.impl

import com.javi.common.Resource
import com.javi.data.datasource.UserDataSource
import com.javi.data.datasource.local.UserPreferences
import com.javi.data.datasource.remote.UserApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
    private val userPreferences: UserPreferences
) : UserDataSource {
    override suspend fun getUser(): Flow<Resource<UserDto>> {
        return flow {
            emit(Resource.Loading(true))

            try {
                val userResult = userApi.getUser()
                val userFromPreferences = userPreferences.getUser().firstOrNull()
                userResult.copy(username = userFromPreferences?.username ?: "")
                delay(2000)
                emit(Resource.Success(userResult))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e))
            }
        }
    }
}