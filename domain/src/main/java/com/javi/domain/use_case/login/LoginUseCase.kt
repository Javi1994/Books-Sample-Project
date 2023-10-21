package com.javi.domain.use_case.login

import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import com.javi.domain.use_case.BaseUseCase
import com.javi.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): BaseUseCase() {

    operator fun invoke(username: String, password: String): Flow<User> {
        return loginRepository.login(username, password).map {
            it.toUser()
        }
    }
}