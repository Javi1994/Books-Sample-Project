package com.javi.testproject.data.remote

import kotlinx.coroutines.flow.Flow

interface LoginApi {
    fun doLogin(username: String, password: String): Flow<Unit>
    fun doLoginWithToken(token: String): Flow<Unit>
}