package com.javi.domain.use_case.book

import com.javi.common.Resource
import com.javi.data.repository.BookRepository
import com.javi.domain.mapping.toBook
import com.javi.domain.model.Book
import com.javi.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavouriteBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : BaseUseCase() {

    operator fun invoke(username: String): Flow<Resource<List<Book>>> {
        return bookRepository.getFavouriteBooks(username).map {
            when(it) {
                is Resource.Success -> {
                    Resource.Success(data = it.data?.map { bookDto ->
                        bookDto.toBook()
                    })
                }
                is Resource.Loading -> { Resource.Loading(isLoading = it.isLoading)}
                is Resource.Error -> { Resource.Error(message = it.message?: "")}
            }
        }
    }
}