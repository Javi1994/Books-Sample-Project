package com.javi.data.datasource.remote

import com.javi.data.dto.UserDto

interface UserApi {
    fun getUser(): UserDto
}