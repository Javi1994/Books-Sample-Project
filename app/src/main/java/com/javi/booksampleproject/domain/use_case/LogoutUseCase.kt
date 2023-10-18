package com.javi.booksampleproject.domain.use_case

import com.javi.booksampleproject.domain.use_case.base.BaseUseCase
import com.javi.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase() {

    operator fun invoke(): Flow<Unit> {
        return userRepository.logout()
    }
}