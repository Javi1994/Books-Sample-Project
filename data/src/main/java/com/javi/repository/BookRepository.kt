package com.javi.repository

import com.javi.dto.BookDetailDto
import com.javi.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getFavouriteBooks(username: String): Flow<List<BookDto>>
    fun getBookDetail(id: String): Flow<BookDetailDto>
}