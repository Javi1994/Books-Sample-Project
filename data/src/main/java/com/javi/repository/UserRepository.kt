package com.javi.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun logout(): Flow<Unit>
}