package com.javi.booksampleproject.domain.use_case

import com.javi.booksampleproject.domain.mapping.toUser
import com.javi.booksampleproject.domain.model.User
import com.javi.booksampleproject.domain.use_case.base.BaseUseCase
import com.javi.data.repository.LoginRepository
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