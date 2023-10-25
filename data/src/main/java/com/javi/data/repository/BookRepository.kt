package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>>
    suspend fun getAllBooks(): Flow<Resource<List<BookDto>>>
    suspend fun getBookDetail(id: String): Flow<Resource<BookDetailDto>>
}