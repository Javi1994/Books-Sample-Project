package com.javi.use_case

import com.javi.mapping.toUser
import com.javi.model.User
import com.javi.use_case.base.BaseUseCase
import com.javi.repository.LoginRepository
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