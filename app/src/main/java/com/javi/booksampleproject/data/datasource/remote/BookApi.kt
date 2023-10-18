package com.javi.booksampleproject.data.datasource.remote

import com.javi.booksampleproject.data.dto.BookDetailDto
import com.javi.booksampleproject.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookApi {
    fun getFavouriteBooks(username: String): Flow<List<BookDto>>
    fun getBookDetail(id: String): Flow<BookDetailDto>
}