package com.javi.testproject.domain.use_case

import com.javi.testproject.data.remote.dto.UserDto
import com.javi.testproject.domain.LoginRepository
import com.javi.testproject.domain.UserRepository
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class LogoutUseCase(
    private val userRepository: UserRepository
): BaseUseCase() {

    operator fun invoke(): Flow<Unit> {
        return userRepository.logout()
    }
}