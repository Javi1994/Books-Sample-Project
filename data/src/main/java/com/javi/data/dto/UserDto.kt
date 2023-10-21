package com.javi.data.dto

data class UserDto(
    val username: String,
    val token: String,
    val name: String = "",
    val lastName: String = "",
    val email: String = ""
)