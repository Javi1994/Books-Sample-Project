package com.javi.data.datasource.mock

import com.javi.data.datasource.remote.LoginApi
import com.javi.data.dto.UserDto

class LoginApiMock : LoginApi {
    override suspend fun doLogin(username: String, password: String): UserDto {
        //delay(2000)
        //throw IOException("There was an error on the server")
        return UserDto("Javi", "sflkADlaÑGgre")
    }

    override suspend fun doLoginWithToken(token: String): UserDto {
        return UserDto("Javi", "sflkADlaÑGgre")
    }

    override suspend fun doLogout() {

    }
}