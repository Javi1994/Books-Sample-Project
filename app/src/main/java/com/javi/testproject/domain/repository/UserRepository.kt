package com.javi.testproject.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun logout(): Flow<Unit>
}