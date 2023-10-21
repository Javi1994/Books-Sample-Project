package com.javi.data.repository.impl

import com.javi.data.datasource.UserDataSource
import com.javi.data.dto.UserDto
import com.javi.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override fun getUser(): Flow<UserDto> {
        return userDataSource.getUser()
    }
}