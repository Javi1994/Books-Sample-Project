package com.javi.domain.use_case.login

import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import com.javi.data.repository.LoginRepository
import com.javi.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginWithTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase() {

    operator fun invoke(token: String): Flow<User> {
        return loginRepository.loginWithToken(token).map {
            it.toUser()
        }
    }
}