package com.javi.testproject.data.mock

import com.javi.testproject.data.remote.LoginApi
import com.javi.testproject.data.remote.dto.UserDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginMock : LoginApi {
    override fun doLogin(username: String, password: String): Flow<UserDto> {
        return flow {
            delay(2000)
            emit(
                UserDto(
                    "Javi",
                    "sflkADlaÑGgre"
                )
            )
        }
    }

    override fun doLoginWithToken(token: String): Flow<UserDto> {
        return flow {
            delay(2000)
            emit(
                UserDto(
                    "Javi",
                    "sflkADlaÑGgre"
                )
            )
        }
    }
}