package com.javi.data.repository

import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<UserDto>
    fun getAllUsers(): Flow<List<UserDto>>
}