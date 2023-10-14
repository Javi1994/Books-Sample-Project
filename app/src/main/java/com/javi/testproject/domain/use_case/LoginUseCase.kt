package com.javi.testproject.domain.use_case

import com.javi.testproject.data.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val loginRepository: LoginRepository
): BaseUseCase() {

    operator fun invoke(username: String, password: String): Flow<Unit> {
        return loginRepository.login(username, password)
    }
}