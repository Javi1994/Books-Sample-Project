package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeBookRepository : BookRepository {

    private var allBooks = listOf<BookDto>()
    private var favouriteBooks = listOf<BookDto>()
    private var bookDetail = BookDetailDto()

    private var shouldReturnError = false
    private var error: Exception? = null

    private var shouldReturnLoading = false

    override suspend fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                if (favouriteBooks.isNotEmpty()) {
                    emit(Resource.Success(favouriteBooks))
                } else {
                    emit(Resource.Error(NullPointerException("No Books")))
                }
            }
        }
    }

    override suspend fun getAllBooks(): Flow<Resource<List<BookDto>>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                if (allBooks.isNotEmpty()) {
                    emit(Resource.Success(allBooks))
                } else {
                    emit(Resource.Error(NullPointerException("No Books")))
                }
            }
        }
    }

    override suspend fun getBookDetail(id: String): Flow<Resource<BookDetailDto>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                if (bookDetail.id == "Id") {
                    emit(Resource.Error(NullPointerException("No Book Detail")))
                } else {
                    emit(Resource.Success(bookDetail))
                }
            }
        }
    }

    fun setFavouriteBooksData(books: List<BookDto>) {
        this.favouriteBooks = books
        this.shouldReturnError = false
        this.shouldReturnLoading = false
    }

    fun setAllBooksData(books: List<BookDto>) {
        this.allBooks = books
        this.shouldReturnError = false
        this.shouldReturnLoading = false
    }

    fun setBookDetailData(bookDetail: BookDetailDto) {
        this.bookDetail = bookDetail
        this.shouldReturnError = false
        this.shouldReturnLoading = false
    }

    fun shouldReturnError(error: Exception? = IOException("Undefined Error")) {
        this.shouldReturnError = true
        this.error = error
    }

    fun shouldReturnLoading() {
        this.shouldReturnLoading = true
        this.shouldReturnError = false
    }
}