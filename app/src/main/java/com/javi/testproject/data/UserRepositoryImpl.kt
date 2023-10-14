package com.javi.testproject.data

import com.javi.testproject.data.remote.BookApi
import com.javi.testproject.data.remote.LoginApi
import com.javi.testproject.data.remote.UserApi
import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.data.remote.dto.BookDto
import com.javi.testproject.data.remote.dto.UserDto
import com.javi.testproject.domain.BookRepository
import com.javi.testproject.domain.LoginRepository
import com.javi.testproject.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {
    override fun logout(): Flow<Unit> {
        return userApi.logout().onEach {
            println("Logout success")
        }
    }
}