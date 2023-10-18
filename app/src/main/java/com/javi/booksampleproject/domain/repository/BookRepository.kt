package com.javi.booksampleproject.domain.repository

import com.javi.booksampleproject.data.dto.BookDetailDto
import com.javi.booksampleproject.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getFavouriteBooks(username: String): Flow<List<BookDto>>
    fun getBookDetail(id: String): Flow<BookDetailDto>
}