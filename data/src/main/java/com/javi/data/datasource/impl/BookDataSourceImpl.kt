package com.javi.data.datasource.impl

import com.javi.data.datasource.BookDataSource
import com.javi.data.datasource.remote.BookApi
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val bookApi: BookApi
): BookDataSource {
    override fun getFavouriteBooks(username: String): Flow<List<BookDto>> {
        return bookApi.getFavouriteBooks(username)
    }

    override fun getAllBooks(): Flow<List<BookDto>> {
        return bookApi.getAllBooks()
    }

    override fun getBookDetail(id: String): Flow<BookDetailDto> {
        return bookApi.getBookDetail(id)
    }
}