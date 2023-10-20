package com.javi.data.datasource.mock

import com.javi.data.datasource.remote.UserApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserApiMock : UserApi {
    override fun getUser(): Flow<UserDto> {
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

    override fun getAllUsers(): Flow<List<UserDto>> {
        return flow {
            delay(2000)
            emit(
                listOf(
                    UserDto(
                        "Javi",
                        "sflkADlaÑGgre"
                    ),
                    UserDto(
                        "Caselles",
                        "sfDasdalagre"
                    ),
                    UserDto(
                        "Uriel",
                        "asffsf2hae"
                    )
                )
            )
        }
    }

    override fun logout(): Flow<Unit> {
        return flow {
            delay(100)
            emit(Unit)
        }
    }
}