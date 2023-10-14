package com.javi.testproject.domain

import com.javi.testproject.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getFavouriteBooks(username: String, password: String): Flow<UserDto>
    fun logout(): Flow<Unit>
}