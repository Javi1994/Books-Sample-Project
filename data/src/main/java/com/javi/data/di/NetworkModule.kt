package com.javi.data.di

import com.javi.data.datasource.mock.BookApiMock
import com.javi.data.datasource.mock.LoginApiMock
import com.javi.data.datasource.mock.UserApiMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMockBookApi(): BookApiMock {
        return BookApiMock()
    }

    @Provides
    @Singleton
    fun provideMockLoginApi(): LoginApiMock {
        return LoginApiMock()
    }

    @Provides
    @Singleton
    fun provideMockUserApi(): UserApiMock {
        return UserApiMock()
    }
}