package com.javi.data.datasource.impl

import com.javi.data.datasource.UserDataSource
import com.javi.data.datasource.local.UserPreferences
import com.javi.data.datasource.remote.UserApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
    private val userPreferences: UserPreferences
) : UserDataSource {
    override fun getUser(): Flow<UserDto> {
        return userPreferences.getUser()
    }

    override fun getAllUsers(): Flow<List<UserDto>> {
        return userApi.getAllUsers()
    }

    override fun logout(): Flow<Unit> {
        return userApi.logout()
    }
}