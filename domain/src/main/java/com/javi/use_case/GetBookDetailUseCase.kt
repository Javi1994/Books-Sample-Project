package com.javi.use_case

import com.javi.mapping.toBookDetail
import com.javi.model.BookDetail
import com.javi.repository.BookRepository
import com.javi.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : BaseUseCase() {

    operator fun invoke(id: String): Flow<BookDetail> {
        return bookRepository.getBookDetail(id)
            .map {
                it.toBookDetail()
            }
    }
}