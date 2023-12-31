package com.javi.data.di

import androidx.room.Room
import com.javi.data.Constants.DATABASE_NAME
import com.javi.data.datasource.database.AppDatabase
import com.javi.data.datasource.database.BookDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single<BookDao> {
        val database = get<AppDatabase>()
        database.bookDao()
    }
}