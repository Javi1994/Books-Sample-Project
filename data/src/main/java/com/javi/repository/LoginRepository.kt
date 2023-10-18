package com.javi.repository

import com.javi.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Flow<UserDto>
    fun loginWithToken(token: String): Flow<UserDto>
}