package com.javi.datasource.remote

import kotlinx.coroutines.flow.Flow

interface UserApi {
    fun logout(): Flow<Unit>
}