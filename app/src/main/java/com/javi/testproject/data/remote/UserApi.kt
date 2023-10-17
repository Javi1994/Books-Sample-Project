package com.javi.testproject.data.remote

import com.javi.testproject.data.dto.BookDto
import com.javi.testproject.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserApi {
    fun logout(): Flow<Unit>
}