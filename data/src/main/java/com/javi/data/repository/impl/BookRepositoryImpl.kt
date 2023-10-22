package com.javi.data.repository.impl

import com.javi.common.Resource
import com.javi.data.datasource.BookDataSource
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import com.javi.data.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookDataSource: BookDataSource
) : BookRepository {

    override fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>> {
        return bookDataSource.getFavouriteBooks(username)
    }

    override fun getAllBooks(): Flow<Resource<List<BookDto>>> {
        return bookDataSource.getAllBooks()
    }

    override fun getBookDetail(id: String): Flow<Resource<BookDetailDto>> {
        return bookDataSource.getBookDetail(id)
    }
}