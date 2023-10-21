package com.javi.data.datasource.impl

import com.javi.data.datasource.UserDataSource
import com.javi.data.datasource.remote.UserApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
) : UserDataSource {
    override fun getUser(): Flow<UserDto> {
        return userApi.getUser()
    }

    override fun getAllUsers(): Flow<List<UserDto>> {
        return userApi.getAllUsers()
    }

    override fun logout(): Flow<Unit> {
        return userApi.logout()
    }
}