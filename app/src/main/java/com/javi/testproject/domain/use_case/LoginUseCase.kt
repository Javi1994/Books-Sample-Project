package com.javi.testproject.domain.use_case

import com.javi.testproject.data.dto.UserDto
import com.javi.testproject.domain.LoginRepository
import com.javi.testproject.domain.mapping.toUser
import com.javi.testproject.domain.model.User
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginUseCase(
    private val loginRepository: LoginRepository
): BaseUseCase() {

    operator fun invoke(username: String, password: String): Flow<User> {
        return loginRepository.login(username, password).map {
            it.toUser()
        }
    }
}