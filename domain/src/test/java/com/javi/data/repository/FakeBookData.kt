package com.javi.data.repository

import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto

object FakeBookData {
    val validBooks = listOf<BookDto>()
    val emptyBooks = listOf<BookDto>()

    val validBookDetailData = BookDetailDto()
    val noValidBookDetailData = BookDetailDto()
}