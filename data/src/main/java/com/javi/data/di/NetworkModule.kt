package com.javi.data.di

import com.javi.data.datasource.mock.BookApiMock
import com.javi.data.datasource.mock.LoginApiMock
import com.javi.data.datasource.mock.UserApiMock
import com.javi.data.datasource.remote.BookApi
import com.javi.data.datasource.remote.LoginApi
import com.javi.data.datasource.remote.UserApi
import org.koin.dsl.module

val networkModule = module {
    single<BookApi> {
        BookApiMock()
    }
    single<LoginApi> {
        LoginApiMock()
    }
    single<UserApi> {
        UserApiMock()
    }
}