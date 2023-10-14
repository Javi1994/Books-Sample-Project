package com.javi.testproject.data

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Flow<Unit>
    fun loginWithToken(token: String): Flow<Unit>
}