package com.javi.testproject.data.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun logout(): Flow<Unit>
}