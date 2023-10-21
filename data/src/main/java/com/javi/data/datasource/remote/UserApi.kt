package com.javi.data.datasource.remote

import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserApi {
    fun getUser(): Flow<UserDto>
    fun getAllUsers(): Flow<List<UserDto>>
}