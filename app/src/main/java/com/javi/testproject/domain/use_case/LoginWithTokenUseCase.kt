package com.javi.testproject.domain.use_case

import com.javi.testproject.data.remote.dto.UserDto
import com.javi.testproject.domain.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginWithTokenUseCase(
    private val loginRepository: LoginRepository
): BaseUseCase() {

    operator fun invoke(token: String): Flow<UserDto> {
        return loginRepository.loginWithToken(token)
    }
}