package com.javi.booksampleproject.data.repository

import com.javi.booksampleproject.data.datasource.remote.UserApi
import com.javi.booksampleproject.domain.repository.UserRepository
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