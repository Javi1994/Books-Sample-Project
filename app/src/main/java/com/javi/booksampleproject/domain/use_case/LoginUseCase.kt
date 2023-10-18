package com.javi.booksampleproject.domain.use_case

import com.javi.booksampleproject.domain.mapping.toUser
import com.javi.booksampleproject.domain.model.User
import com.javi.booksampleproject.domain.repository.LoginRepository
import com.javi.booksampleproject.domain.use_case.base.BaseUseCase
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