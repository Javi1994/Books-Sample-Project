package com.javi.data.datasource

import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun login(username: String, password: String): Flow<UserDto>
    fun loginWithToken(token: String): Flow<UserDto>
    fun logout(): Flow<Unit>
}