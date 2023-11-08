package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeUserRepository : UserRepository {

    private var user: UserDto? = null

    private var shouldReturnError = false
    private var error: Exception? = null

    private var shouldReturnLoading = false

    override suspend fun getUser(): Flow<Resource<UserDto>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                if (user?.username?.isEmpty() == true && user?.token?.isEmpty() == true) {
                    emit(Resource.Error(NullPointerException("No User Data")))
                } else {
                    emit(Resource.Success(user))
                }
            }
        }
    }

    fun setUserData(user: UserDto) {
        this.user = user
        this.shouldReturnError = false
        this.shouldReturnLoading = false
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