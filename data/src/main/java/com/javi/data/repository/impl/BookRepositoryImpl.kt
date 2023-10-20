package com.javi.data.repository.impl

import com.javi.data.datasource.BookDataSource
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import com.javi.data.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookDataSource: BookDataSource
) : BookRepository {

    override fun getFavouriteBooks(username: String): Flow<List<BookDto>> {
        return bookDataSource.getFavouriteBooks(username)
            .onEach {
                println("Favourite books result: $it")
            }
    }

    override fun getAllBooks(): Flow<List<BookDto>> {
        return bookDataSource.getAllBooks()
            .onEach {
                println("All books result: $it")
            }
    }

    override fun getBookDetail(id: String): Flow<BookDetailDto> {
        return bookDataSource.getBookDetail(id)
            .onEach {
                println("Book detail result: $it")
            }
    }
}