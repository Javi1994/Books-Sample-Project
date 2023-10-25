package com.javi.data.datasource

import com.javi.common.Resource
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookDataSource {
    suspend fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>>
    suspend fun getAllBooks(): Flow<Resource<List<BookDto>>>
    suspend fun getBookDetail(id: String): Flow<Resource<BookDetailDto>>
}