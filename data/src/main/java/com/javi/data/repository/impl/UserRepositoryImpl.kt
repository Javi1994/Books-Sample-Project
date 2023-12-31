package com.javi.data.repository.impl

import com.javi.common.Resource
import com.javi.data.datasource.UserDataSource
import com.javi.data.dto.UserDto
import com.javi.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override suspend fun getUser(): Flow<Resource<UserDto>> {
        return userDataSource.getUser()
    }
}