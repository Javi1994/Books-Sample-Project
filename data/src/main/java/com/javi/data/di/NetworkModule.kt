package com.javi.data.di

import com.javi.data.datasource.mock.BookApiMock
import com.javi.data.datasource.mock.LoginApiMock
import com.javi.data.datasource.mock.UserApiMock
import org.koin.dsl.module

val networkModule = module {
    single {
        BookApiMock()
    }
    single {
        LoginApiMock()
    }
    single {
        UserApiMock()
    }
}