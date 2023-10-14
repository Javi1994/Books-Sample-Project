package com.javi.testproject.data

import com.javi.testproject.data.remote.BookApi
import com.javi.testproject.data.remote.LoginApi
import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.data.remote.dto.BookDto
import com.javi.testproject.data.remote.dto.UserDto
import com.javi.testproject.domain.BookRepository
import com.javi.testproject.domain.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class BookRepositoryImpl(
    private val bookApi: BookApi
) : BookRepository {
    override fun getFavouriteBooks(username: String): Flow<List<BookDto>> {
        return bookApi.getFavouriteBooks(username).onEach {
            println("Favourite books result: $it")
        }
    }

    override fun getBookDetail(id: String): Flow<BookDetailDto> {
        return bookApi.getBookDetail(id).onEach {
            println("Favourite books result: $it")
        }
    }
}