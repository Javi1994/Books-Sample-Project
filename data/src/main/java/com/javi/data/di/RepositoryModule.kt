package com.javi.data.di

import com.javi.data.repository.BookRepository
import com.javi.data.repository.LoginRepository
import com.javi.data.repository.UserRepository
import com.javi.data.repository.impl.BookRepositoryImpl
import com.javi.data.repository.impl.LoginRepositoryImpl
import com.javi.data.repository.impl.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<BookRepository> {
        BookRepositoryImpl(get())
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}