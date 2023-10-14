package com.javi.testproject.domain

import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.data.remote.dto.BookDto
import com.javi.testproject.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getFavouriteBooks(username: String): Flow<List<BookDto>>
    fun getBookDetail(id: String): Flow<BookDetailDto>
}