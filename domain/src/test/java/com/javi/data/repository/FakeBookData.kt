package com.javi.data.repository

import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto

object FakeBookData {
    val validBooks = listOf(BookDto(), BookDto())
    val emptyBooks = listOf<BookDto>()

    val validBookDetailData = BookDetailDto(
        "1",
        "Title",
        "Description",
        250,
        360,
        "Me"
    )
    val emptyBookDetailData = BookDetailDto()
    val noValidBookDetailData = BookDetailDto()
}