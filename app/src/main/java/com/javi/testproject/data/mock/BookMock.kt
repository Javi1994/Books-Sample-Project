package com.javi.testproject.data.mock

import com.javi.testproject.data.remote.BookApi
import com.javi.testproject.data.remote.dto.BookDetailDto
import com.javi.testproject.data.remote.dto.BookDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookMock: BookApi {

    override fun getFavouriteBooks(username: String): Flow<List<BookDto>> {
        return flow {
            delay(1000)
            emit(
                listOf(
                    BookDto(),
                    BookDto(),
                    BookDto(),
                    BookDto(),
                    BookDto(),
                    BookDto()
                )
            )
        }
    }

    override fun getBookDetail(id: String): Flow<BookDetailDto> {
        return flow {
            delay(1000)
            emit(
                BookDetailDto(
                "",
                "",
                "",
                250,
                100,
                ""
            )
            )
        }
    }
}