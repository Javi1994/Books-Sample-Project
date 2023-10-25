package com.javi.domain.use_case.user

import com.javi.common.Resource
import com.javi.data.repository.UserRepository
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(): Flow<Resource<User>> = withContext(defaultDispatcher) {
        return@withContext userRepository.getUser().map {
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