package com.javi.booksampleproject.domain.use_case

import com.javi.booksampleproject.domain.repository.UserRepository
import com.javi.booksampleproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase() {

    operator fun invoke(): Flow<Unit> {
        return userRepository.logout()
    }
}