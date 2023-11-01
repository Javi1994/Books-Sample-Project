package com.javi.data.di

import com.javi.data.datasource.BookDataSource
import com.javi.data.datasource.LoginDataSource
import com.javi.data.datasource.UserDataSource
import com.javi.data.datasource.impl.BookDataSourceImpl
import com.javi.data.datasource.impl.LoginDataSourceImpl
import com.javi.data.datasource.impl.UserDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    includes(databaseModule)
    includes(preferencesModule)
    includes(networkModule)
    includes(dispatcherModule)

    single<UserDataSource> {
        UserDataSourceImpl(get(), get())
    }

    single<BookDataSource> {
        BookDataSourceImpl(get(), get(), get())
    }

    single<LoginDataSource> {
        LoginDataSourceImpl(get(), get(), get(), get())
    }
}