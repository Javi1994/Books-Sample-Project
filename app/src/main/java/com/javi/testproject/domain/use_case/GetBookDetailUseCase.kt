package com.javi.testproject.domain.use_case

import com.javi.testproject.data.dto.BookDetailDto
import com.javi.testproject.domain.BookRepository
import com.javi.testproject.domain.mapping.toBook
import com.javi.testproject.domain.mapping.toBookDetail
import com.javi.testproject.domain.model.BookDetail
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBookDetailUseCase(
    private val bookRepository: BookRepository
) : BaseUseCase() {

    operator fun invoke(id: String): Flow<BookDetail> {
        return bookRepository.getBookDetail(id)
            .map {
                it.toBookDetail()
            }
    }
}