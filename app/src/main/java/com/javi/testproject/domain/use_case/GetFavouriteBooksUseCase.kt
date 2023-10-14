package com.javi.testproject.domain.use_case

import com.javi.testproject.data.remote.dto.BookDto
import com.javi.testproject.data.remote.dto.UserDto
import com.javi.testproject.domain.BookRepository
import com.javi.testproject.domain.LoginRepository
import com.javi.testproject.domain.UserRepository
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetFavouriteBooksUseCase(
    private val bookRepository: BookRepository
): BaseUseCase() {

    operator fun invoke(username: String): Flow<List<BookDto>> {
        return bookRepository.getFavouriteBooks(username)
    }
}