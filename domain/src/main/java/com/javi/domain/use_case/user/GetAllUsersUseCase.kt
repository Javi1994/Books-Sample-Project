package com.javi.domain.use_case.user

import com.javi.data.repository.UserRepository
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import com.javi.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase() {

    operator fun invoke(): Flow<List<User>> {
        return userRepository.getAllUsers()
            .map { list ->
                list.map {
                    it.toUser()
                }
            }
    }
}