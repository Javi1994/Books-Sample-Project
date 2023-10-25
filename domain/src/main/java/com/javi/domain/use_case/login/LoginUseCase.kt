package com.javi.domain.use_case.login

import com.javi.common.Resource
import com.javi.data.repository.LoginRepository
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(username: String, password: String): Flow<Resource<User>> =
        withContext(defaultDispatcher) {
            return@withContext loginRepository.login(username, password).map {
                when (it) {
                    is Resource.Success -> {
                        Resource.Success(data = it.data?.toUser())
                    }

                    is Resource.Loading -> {
                        Resource.Loading(isLoading = it.isLoading)
                    }

                    is Resource.Error -> {
                        Resource.Error(error = it.error)
                    }
                }
            }
        }
}