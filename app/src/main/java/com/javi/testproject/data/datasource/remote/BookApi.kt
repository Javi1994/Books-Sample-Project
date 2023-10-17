package com.javi.testproject.data.datasource.remote

import com.javi.testproject.data.dto.BookDetailDto
import com.javi.testproject.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookApi {
    fun getFavouriteBooks(username: String): Flow<List<BookDto>>
    fun getBookDetail(id: String): Flow<BookDetailDto>
}