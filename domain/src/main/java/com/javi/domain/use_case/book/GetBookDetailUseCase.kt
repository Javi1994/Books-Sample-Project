package com.javi.domain.use_case.book

import com.javi.common.Resource
import com.javi.data.repository.BookRepository
import com.javi.domain.mapping.toBookDetail
import com.javi.domain.model.BookDetail
import com.javi.domain.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : BaseUseCase() {

    operator fun invoke(id: String): Flow<Resource<BookDetail>> {
        return bookRepository.getBookDetail(id).map {
            when(it) {
                is Resource.Success -> { Resource.Success(data = it.data?.toBookDetail())}
                is Resource.Loading -> { Resource.Loading(isLoading = it.isLoading)}
                is Resource.Error -> { Resource.Error(message = it.message?: "")}
            }
        }
    }
}