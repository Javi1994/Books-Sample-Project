package com.javi.booksampleproject.domain.use_case

import com.javi.booksampleproject.domain.mapping.toBookDetail
import com.javi.booksampleproject.domain.model.BookDetail
import com.javi.booksampleproject.domain.use_case.base.BaseUseCase
import com.javi.data.repository.BookRepository
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