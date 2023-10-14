package com.javi.testproject.data.remote

import com.javi.testproject.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserApi {
    fun getFavouriteBooks(): Flow<UserDto>
    fun doLoginWithToken(token: String): Flow<UserDto>
}