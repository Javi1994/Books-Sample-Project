package com.javi.booksampleproject.di

import com.javi.booksampleproject.data.datasource.mock.BookApiMock
import com.javi.booksampleproject.data.datasource.mock.LoginApiMock
import com.javi.booksampleproject.data.datasource.mock.UserApiMock
import com.javi.booksampleproject.data.repository.BookRepositoryImpl
import com.javi.booksampleproject.data.repository.LoginRepositoryImpl
import com.javi.booksampleproject.data.repository.UserRepositoryImpl
import com.javi.booksampleproject.domain.repository.BookRepository
import com.javi.booksampleproject.domain.repository.LoginRepository
import com.javi.booksampleproject.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(
        bookApiMock: BookApiMock
    ): BookRepository {
        return BookRepositoryImpl(bookApiMock)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userApiMock: UserApiMock
    ): UserRepository {
        return UserRepositoryImpl(userApiMock)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginApiMock: LoginApiMock
    ): LoginRepository {
        return LoginRepositoryImpl(loginApiMock)
    }
}