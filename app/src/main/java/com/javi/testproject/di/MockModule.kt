package com.javi.testproject.di

import com.javi.testproject.data.datasource.mock.BookApiMock
import com.javi.testproject.data.datasource.mock.LoginApiMock
import com.javi.testproject.data.datasource.mock.UserApiMock
import com.javi.testproject.data.datasource.remote.BookApi
import com.javi.testproject.data.datasource.remote.LoginApi
import com.javi.testproject.data.datasource.remote.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MockModule {

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