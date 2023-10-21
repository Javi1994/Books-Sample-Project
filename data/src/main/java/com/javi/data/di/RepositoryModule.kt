package com.javi.data.di

import com.javi.data.datasource.BookDataSource
import com.javi.data.datasource.LoginDataSource
import com.javi.data.datasource.UserDataSource
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
        bookDataSource: BookDataSource
    ): BookRepository {
        return BookRepositoryImpl(bookDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDataSource: UserDataSource
    ): UserRepository {
        return UserRepositoryImpl(userDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginDataSource: LoginDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(loginDataSource)
    }
}