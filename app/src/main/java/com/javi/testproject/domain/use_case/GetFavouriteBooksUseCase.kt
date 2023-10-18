package com.javi.testproject.domain.use_case

import com.javi.testproject.domain.repository.BookRepository
import com.javi.testproject.domain.mapping.toBook
import com.javi.testproject.domain.model.Book
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavouriteBooksUseCase @Inject constructor(
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