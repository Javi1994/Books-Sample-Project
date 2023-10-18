package com.javi.booksampleproject.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun logout(): Flow<Unit>
}