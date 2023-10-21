package com.javi.data.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.javi.data.dto.BookDto
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM bookdto")
    fun getAllBooks(): Flow<List<BookDto>>

    @Insert
    fun insertAll(books: List<BookDto>)

    @Delete
    fun delete(book: BookDto)
}