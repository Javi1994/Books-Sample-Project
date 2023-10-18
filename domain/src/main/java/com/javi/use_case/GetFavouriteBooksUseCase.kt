package com.javi.use_case

import com.javi.mapping.toBook
import com.javi.model.Book
import com.javi.repository.BookRepository
import com.javi.use_case.base.BaseUseCase
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