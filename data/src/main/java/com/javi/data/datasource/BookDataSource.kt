package com.javi.data.datasource

import com.javi.common.Resource
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

interface BookDataSource {
    fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>>
    fun getAllBooks(): Flow<Resource<List<BookDto>>>
    fun getBookDetail(id: String): Flow<Resource<BookDetailDto>>
}