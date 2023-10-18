package com.javi.booksampleproject.data.datasource.remote

import com.javi.booksampleproject.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface LoginApi {
    fun doLogin(username: String, password: String): Flow<UserDto>
    fun doLoginWithToken(token: String): Flow<UserDto>
}