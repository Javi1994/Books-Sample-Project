package com.javi.data.datasource

import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookDataSource {
    fun getFavouriteBooks(username: String): Flow<List<BookDto>>
    fun getAllBooks(): Flow<List<BookDto>>
    fun getBookDetail(id: String): Flow<BookDetailDto>
}