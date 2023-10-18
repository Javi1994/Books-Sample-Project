package com.javi.testproject.domain.use_case

import com.javi.testproject.domain.repository.LoginRepository
import com.javi.testproject.domain.mapping.toUser
import com.javi.testproject.domain.model.User
import com.javi.testproject.domain.use_case.base.BaseUseCase
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