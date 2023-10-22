package com.javi.data.datasource.remote

import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto

interface BookApi {
    fun getFavouriteBooks(username: String): List<BookDto>
    fun getAllBooks(): List<BookDto>
    fun getBookDetail(id: String): BookDetailDto
}