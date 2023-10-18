package com.javi.testproject.data.repository

import com.javi.testproject.data.datasource.remote.UserApi
import com.javi.testproject.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {
    override fun logout(): Flow<Unit> {
        return userApi.logout()
            .onEach {
            println("Logout success")
        }
    }
}