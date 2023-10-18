package com.javi.testproject.di

import com.javi.testproject.data.datasource.mock.BookApiMock
import com.javi.testproject.data.datasource.mock.LoginApiMock
import com.javi.testproject.data.datasource.mock.UserApiMock
import com.javi.testproject.data.datasource.remote.BookApi
import com.javi.testproject.data.datasource.remote.LoginApi
import com.javi.testproject.data.datasource.remote.UserApi
import com.javi.testproject.data.repository.BookRepositoryImpl
import com.javi.testproject.data.repository.LoginRepositoryImpl
import com.javi.testproject.data.repository.UserRepositoryImpl
import com.javi.testproject.domain.repository.BookRepository
import com.javi.testproject.domain.repository.LoginRepository
import com.javi.testproject.domain.repository.UserRepository
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