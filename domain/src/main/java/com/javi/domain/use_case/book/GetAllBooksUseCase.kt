package com.javi.domain.use_case.book

import com.javi.common.Resource
import com.javi.data.repository.BookRepository
import com.javi.domain.mapping.toBook
import com.javi.domain.model.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.koin.core.component.getScopeName

class GetAllBooksUseCase constructor(
    private val bookRepository: BookRepository,
    private val defaultDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<Resource<List<Book>>> = withContext(defaultDispatcher) {
        val bookRepository1 = bookRepository
        bookRepository1.getScopeName()
        return@withContext bookRepository.getAllBooks().map {
            when (it) {
                is Resource.Success -> {
                    Resource.Success(data = it.data?.map { bookDto ->
                        bookDto.toBook()
                    })
                }

                is Resource.Loading -> {
                    Resource.Loading(isLoading = it.isLoading)
                }

                is Resource.Error -> {
                    Resource.Error(error = it.error)
                }
            }
        }
    }
}