package com.javi.testproject.domain.use_case

import com.javi.testproject.domain.repository.UserRepository
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class LogoutUseCase(
    private val userRepository: UserRepository
): BaseUseCase() {

    operator fun invoke(): Flow<Unit> {
        return userRepository.logout()
    }
}