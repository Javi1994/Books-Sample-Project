package com.javi.data.datasource.impl

import com.javi.common.Resource
import com.javi.data.datasource.BookDataSource
import com.javi.data.datasource.database.BookDao
import com.javi.data.datasource.remote.BookApi
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val bookApi: BookApi,
    private val bookDao: BookDao
) : BookDataSource {
    override fun getFavouriteBooks(username: String): Flow<Resource<List<BookDto>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val favouriteResult = bookApi.getFavouriteBooks(username)
                delay(2000)
                emit(Resource.Success(favouriteResult))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Undefined error"))
            }
        }
    }

    override fun getAllBooks(): Flow<Resource<List<BookDto>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val allBooksResult = bookApi.getAllBooks()
                delay(2000)
                emit(Resource.Success(allBooksResult))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Undefined error"))
            }
        }
    }

    override fun getBookDetail(id: String): Flow<Resource<BookDetailDto>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val bookDetailResult = bookApi.getBookDetail(id)
                delay(2000)
                emit(Resource.Success(bookDetailResult))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Undefined error"))
            }
        }
    }
}