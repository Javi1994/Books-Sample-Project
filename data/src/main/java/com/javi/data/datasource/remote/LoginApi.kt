package com.javi.data.datasource.remote

import com.javi.data.dto.UserDto

interface LoginApi {
    fun doLogin(username: String, password: String): UserDto
    fun doLoginWithToken(token: String): UserDto
    fun doLogout(): Unit
}