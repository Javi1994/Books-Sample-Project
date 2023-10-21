package com.javi.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javi.data.dto.BookDto

@Database(entities = [BookDto::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}