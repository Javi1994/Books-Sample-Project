package com.javi.data.repository.impl

import com.javi.common.Resource
import com.javi.data.datasource.BookDataSource
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import com.javi.data.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl constructor(
    private val bookDataSource: BookDataSource
) : BookRepository {

    override suspend fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>> {
        return bookDataSource.getFavouriteBooks(username)
    }

    override suspend fun getAllBooks(): Flow<Resource<List<BookDto>>> {
        return bookDataSource.getAllBooks()
    }

    override suspend fun getBookDetail(id: String): Flow<Resource<BookDetailDto>> {
        return bookDataSource.getBookDetail(id)
    }
}