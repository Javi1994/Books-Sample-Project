package com.javi.data.datasource.local

import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserPreferences constructor(

) {
    fun getUser(): Flow<UserDto> {
        return flow {
            emit(UserDto("JaviCaselles", "123456789"))
        }
    }
}