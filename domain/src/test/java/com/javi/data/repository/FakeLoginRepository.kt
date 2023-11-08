package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeLoginRepository : LoginRepository {

    private var user: UserDto? = null

    private var shouldReturnError = false
    private var error: Exception? = null

    private var shouldReturnLoading = false

    override suspend fun login(username: String, password: String): Flow<Resource<UserDto>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                if (user?.username?.isEmpty() == true && user?.token?.isEmpty() == true) {
                    emit(Resource.Error(NullPointerException("Invalid Login Data")))
                } else {
                    emit(Resource.Success(user))
                }
            }
        }
    }

    override suspend fun loginWithToken(token: String): Flow<Resource<UserDto>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                if (user?.username?.isEmpty() == true && user?.token?.isEmpty() == true) {
                    emit(Resource.Error(NullPointerException("Invalid Login Data")))
                } else {
                    emit(Resource.Success(user))
                }
            }
        }
    }

    override suspend fun logout(): Flow<Resource<Unit>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                emit(Resource.Success(Unit))
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