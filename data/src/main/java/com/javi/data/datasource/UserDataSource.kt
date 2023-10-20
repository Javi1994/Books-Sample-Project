package com.javi.data.datasource

import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getUser(): Flow<UserDto>
    fun getAllUsers(): Flow<List<UserDto>>
    fun logout(): Flow<Unit>
}