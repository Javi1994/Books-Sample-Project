package com.javi.booksampleproject.data.repository

import com.javi.booksampleproject.data.datasource.remote.BookApi
import com.javi.booksampleproject.data.dto.BookDetailDto
import com.javi.booksampleproject.data.dto.BookDto
import com.javi.booksampleproject.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookApi: BookApi
) : BookRepository {
    override fun getFavouriteBooks(username: String): Flow<List<BookDto>> {
        return bookApi.getFavouriteBooks(username)
            .onEach {
                println("Favourite books result: $it")
            }
    }

    override fun getBookDetail(id: String): Flow<BookDetailDto> {
        return bookApi.getBookDetail(id)
            .onEach {
                println("Book detail result: $it")
            }
    }
}