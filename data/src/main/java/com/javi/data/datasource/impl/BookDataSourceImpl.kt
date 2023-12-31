package com.javi.data.datasource.impl

import com.javi.common.Resource
import com.javi.data.datasource.BookDataSource
import com.javi.data.datasource.database.BookDao
import com.javi.data.datasource.remote.BookApi
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException

class BookDataSourceImpl constructor(
    private val bookApi: BookApi,
    private val bookDao: BookDao,
    private val ioDispatcher: CoroutineDispatcher
) : BookDataSource {
    override suspend fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val favouriteResult = bookApi.getFavouriteBooks(username)
                delay(2000)
                emit(Resource.Success(favouriteResult))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun getAllBooks(): Flow<Resource<List<BookDto>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                var bookFromDatabase: List<BookDto>
                withContext(ioDispatcher) {
                    bookFromDatabase = bookDao.getAllBooks()
                }

                if (bookFromDatabase.isEmpty()) {
                    val allBooksResult = bookApi.getAllBooks()
                    delay(15000)
                    emit(Resource.Success(allBooksResult))

                    withContext(ioDispatcher) {
                        bookDao.insertAll(allBooksResult)
                    }

                } else {
                    emit(Resource.Success(bookFromDatabase))
                }

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun getBookDetail(id: String): Flow<Resource<BookDetailDto>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val bookDetailResult = bookApi.getBookDetail(id)
                delay(2000)
                emit(Resource.Success(bookDetailResult))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e))
            }
        }
    }
}