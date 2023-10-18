package com.javi.datasource.remote

import com.javi.dto.BookDetailDto
import com.javi.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookApi {
    fun getFavouriteBooks(username: String): Flow<List<BookDto>>
    fun getBookDetail(id: String): Flow<BookDetailDto>
}