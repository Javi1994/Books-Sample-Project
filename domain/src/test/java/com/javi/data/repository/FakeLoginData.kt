package com.javi.data.repository

import com.javi.data.dto.UserDto

object FakeLoginData {
    val validLogin = UserDto("Javi", "123431sdad")
    val emptyLogin = UserDto("", "")
}