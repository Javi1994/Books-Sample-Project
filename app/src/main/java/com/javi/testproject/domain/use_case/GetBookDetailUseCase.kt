package com.javi.testproject.domain.use_case

import com.javi.testproject.domain.repository.BookRepository
import com.javi.testproject.domain.mapping.toBookDetail
import com.javi.testproject.domain.model.BookDetail
import com.javi.testproject.domain.use_case.base.BaseUseCase
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