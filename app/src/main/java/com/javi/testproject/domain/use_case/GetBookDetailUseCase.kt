package com.javi.testproject.domain.use_case

import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.domain.BookRepository
import com.javi.testproject.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetBookDetailUseCase(
    private val bookRepository: BookRepository
): BaseUseCase() {

    operator fun invoke(id: String): Flow<BookDetailDto> {
        return bookRepository.getBookDetail(id)
    }
}