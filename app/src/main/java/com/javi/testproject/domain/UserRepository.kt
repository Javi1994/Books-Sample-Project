package com.javi.testproject.domain

import com.javi.testproject.data.dto.BookDto
import com.javi.testproject.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun logout(): Flow<Unit>
}