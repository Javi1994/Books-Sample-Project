package com.javi.testproject.data.remote

import com.javi.testproject.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface LoginApi {
    fun doLogin(username: String, password: String): Flow<UserDto>
    fun doLoginWithToken(token: String): Flow<UserDto>
}