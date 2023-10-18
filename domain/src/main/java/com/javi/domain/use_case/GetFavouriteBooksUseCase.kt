package com.javi.domain.use_case

import com.javi.domain.mapping.toBook
import com.javi.domain.model.Book
import com.javi.data.repository.BookRepository
import com.javi.domain.use_case.base.BaseUseCase
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