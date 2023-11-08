package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class FakeUserRepository : UserRepository {

    private var shouldReturnError = false
    private var error: Exception? = null

    private var shouldReturnLoading = false

    override suspend fun getUser(): Flow<Resource<UserDto>> {
        TODO("Not yet implemented")
    }

    fun shouldReturnError(error: Exception? = IOException("Undefined Error")) {
        this.shouldReturnError = true
        this.error = error
    }

    fun shouldReturnLoading() {
        this.shouldReturnLoading = true
        this.shouldReturnError = false
    }
}