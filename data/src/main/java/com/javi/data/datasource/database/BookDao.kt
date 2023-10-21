package com.javi.data.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.javi.data.dto.BookDto

@Dao
interface BookDao {

    @Query("SELECT * FROM bookdao")
    fun getAllBooks(): List<BookDto>

    @Insert
    fun insertAll(vararg books: BookDto)

    @Delete
    fun delete(book: BookDto)
}