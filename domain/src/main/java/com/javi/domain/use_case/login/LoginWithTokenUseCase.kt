package com.javi.domain.use_case.login

import com.javi.common.Resource
import com.javi.data.repository.LoginRepository
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import com.javi.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginWithTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase() {

    operator fun invoke(token: String): Flow<Resource<User>> {
        return loginRepository.loginWithToken(token).map {
            when(it) {
                is Resource.Success -> { Resource.Success(data = it.data?.toUser())}
                is Resource.Loading -> { Resource.Loading(isLoading = it.isLoading)}
                is Resource.Error -> { Resource.Error(error = it.error)}
            }
        }
    }
}