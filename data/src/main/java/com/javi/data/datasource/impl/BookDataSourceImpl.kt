package com.javi.data.datasource.impl

import com.javi.data.datasource.BookDataSource
import com.javi.data.datasource.database.BookDao
import com.javi.data.datasource.remote.BookApi
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val bookApi: BookApi,
    private val bookDao: BookDao
) : BookDataSource {
    override fun getFavouriteBooks(username: String): Flow<List<BookDto>> {
        return bookApi.getFavouriteBooks(username)
    }

    override fun getAllBooks(): Flow<List<BookDto>> {
        return bookDao.getAllBooks()
            .map { list ->
                list.ifEmpty {
                    val books = bookApi.getAllBooks().first()

                    withContext(Dispatchers.IO) {
                        bookDao.insertAll(books)
                    }

                    books
                }
            }
    }

    override fun getBookDetail(id: String): Flow<BookDetailDto> {
        return bookApi.getBookDetail(id)
    }
}