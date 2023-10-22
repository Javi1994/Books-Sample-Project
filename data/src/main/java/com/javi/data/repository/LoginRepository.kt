package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Flow<Resource<UserDto>>
    fun loginWithToken(token: String): Flow<Resource<UserDto>>
    fun logout(): Flow<Resource<Unit>>
}