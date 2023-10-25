package com.javi.domain.use_case.book

import com.javi.common.Resource
import com.javi.data.repository.BookRepository
import com.javi.domain.mapping.toBookDetail
import com.javi.domain.model.BookDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(id: String): Flow<Resource<BookDetail>> =
        withContext(defaultDispatcher) {
            return@withContext bookRepository.getBookDetail(id).map {
                when (it) {
                    is Resource.Success -> {
                        Resource.Success(data = it.data?.toBookDetail())
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