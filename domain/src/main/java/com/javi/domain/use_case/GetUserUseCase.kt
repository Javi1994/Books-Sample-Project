package com.javi.domain.use_case

import com.javi.data.repository.UserRepository
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import com.javi.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase() {

    operator fun invoke(): Flow<User> {
        return userRepository.getUser()
            .map {
                it.toUser()
            }
    }
}