package com.javi.testproject.domain.use_case

import com.javi.testproject.data.dto.BookDto
import com.javi.testproject.data.dto.UserDto
import com.javi.testproject.domain.BookRepository
import com.javi.testproject.domain.LoginRepository
import com.javi.testproject.domain.UserRepository
import com.javi.testproject.domain.mapping.toBook
import com.javi.testproject.domain.model.Book
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavouriteBooksUseCase(
    private val bookRepository: BookRepository
) : BaseUseCase() {

    operator fun invoke(username: String): Flow<List<Book>> {
        return bookRepository.getFavouriteBooks(username)
            .map { list ->
                list.map {
                    it.toBook()
                }
            }
    }
}