package com.javi.data.repository

import com.javi.data.dto.UserDto

object FakeUserData {
    val validUser = UserDto("Javi", "123431sdad")
    val emptyUser = UserDto("", "")
}