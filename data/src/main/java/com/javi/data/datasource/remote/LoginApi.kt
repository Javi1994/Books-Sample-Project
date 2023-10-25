package com.javi.data.datasource.remote

import com.javi.data.dto.UserDto

interface LoginApi {
    suspend fun doLogin(username: String, password: String): UserDto
    suspend fun doLoginWithToken(token: String): UserDto
    suspend fun doLogout()
}