package com.javi.use_case

import com.javi.use_case.base.BaseUseCase
import com.javi.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase() {

    operator fun invoke(): Flow<Unit> {
        return userRepository.logout()
    }
}