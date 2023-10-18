package com.javi.testproject.data.repository

import com.javi.testproject.data.datasource.remote.BookApi
import com.javi.testproject.data.dto.BookDetailDto
import com.javi.testproject.data.dto.BookDto
import com.javi.testproject.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class BookRepositoryImpl(
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