package com.javi.booksampleproject.data.datasource.remote

import kotlinx.coroutines.flow.Flow

interface UserApi {
    fun logout(): Flow<Unit>
}