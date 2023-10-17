package com.javi.testproject.data

import com.javi.testproject.data.datasource.remote.UserApi
import com.javi.testproject.data.repository.UserRepository
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