package com.javi.domain.use_case.login

import com.javi.common.Resource
import com.javi.data.repository.LoginRepository
import com.javi.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): BaseUseCase() {

    operator fun invoke(): Flow<Resource<Unit>> {
        return loginRepository.logout()
    }
}