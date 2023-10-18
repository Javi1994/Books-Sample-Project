package com.javi.data.repository.impl

import com.javi.data.datasource.remote.UserApi
import com.javi.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override fun logout(): Flow<Unit> {
        return userApi.logout()
            .onEach {
            println("Logout success")
        }
    }
}