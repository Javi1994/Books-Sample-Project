package com.javi.data.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.javi.data.dto.BookDto

@Dao
interface BookDao {

    @Query("SELECT * FROM bookdto")
    suspend fun getAllBooks(): List<BookDto>

    @Insert
    suspend fun insertAll(books: List<BookDto>)

    @Delete
    suspend fun delete(book: BookDto)

    @Query("DELETE FROM bookdto")
    suspend fun deleteAll()
}