package com.javi.data.datasource.mock

import com.javi.data.datasource.remote.UserApi
import com.javi.data.dto.UserDto

class UserApiMock : UserApi {
    override suspend fun getUser(): UserDto {
        return UserDto(
            "Javi1994",
            "sflkADlaÑGgre",
            "Javier",
            "Caselles",
            "javi@email.com"
        )
    }
}