package com.javi.domain.use_case.login

import com.javi.common.Resource
import com.javi.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LogoutUseCase constructor(
    private val loginRepository: LoginRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(): Flow<Resource<Unit>> = withContext(defaultDispatcher) {
        return@withContext loginRepository.logout()
    }
}