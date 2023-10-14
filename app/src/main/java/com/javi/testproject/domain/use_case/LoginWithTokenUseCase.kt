package com.javi.testproject.domain.use_case

import com.javi.testproject.data.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginWithTokenUseCase(
    private val loginRepository: LoginRepository
): BaseUseCase() {

    operator fun invoke(token: String): Flow<Unit> {
        return loginRepository.loginWithToken(token)
    }
}