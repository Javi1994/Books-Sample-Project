package com.javi.di

import com.javi.data.datasource.mock.BookApiMock
import com.javi.data.datasource.mock.LoginApiMock
import com.javi.data.datasource.mock.UserApiMock
import com.javi.data.repository.BookRepository
import com.javi.data.repository.LoginRepository
import com.javi.data.repository.UserRepository
import com.javi.data.repository.impl.BookRepositoryImpl
import com.javi.data.repository.impl.LoginRepositoryImpl
import com.javi.data.repository.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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