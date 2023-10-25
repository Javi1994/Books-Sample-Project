package com.javi.data.datasource.remote

import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto

interface BookApi {
    suspend fun getFavouriteBooks(username: String): List<BookDto>
    suspend fun getAllBooks(): List<BookDto>
    suspend fun getBookDetail(id: String): BookDetailDto
}