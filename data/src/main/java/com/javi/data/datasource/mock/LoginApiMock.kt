package com.javi.data.datasource.mock

import com.javi.data.datasource.remote.LoginApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginApiMock : LoginApi {
    override fun doLogin(username: String, password: String): UserDto {
        return UserDto("Javi", "sflkADlaÑGgre")
    }

    override fun doLoginWithToken(token: String): UserDto {
        return UserDto("Javi", "sflkADlaÑGgre")
    }

    override fun doLogout() {

    }
}