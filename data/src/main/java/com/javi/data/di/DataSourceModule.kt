package com.javi.data.di

import com.javi.data.datasource.BookDataSource
import com.javi.data.datasource.UserDataSource
import com.javi.data.datasource.impl.BookDataSourceImpl
import com.javi.data.datasource.impl.UserDataSourceImpl
import com.javi.data.datasource.local.UserPreferences
import com.javi.data.datasource.mock.BookApiMock
import com.javi.data.datasource.mock.UserApiMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideUserDataSource(
        mockUserApiMock: UserApiMock,
        preferences: UserPreferences
    ): UserDataSource {
        return UserDataSourceImpl(mockUserApiMock, preferences)
    }

    @Provides
    @Singleton
    fun provideBooksDataSource(
        mockBookApiMock: BookApiMock,
    ): BookDataSource {
        return BookDataSourceImpl(mockBookApiMock)
    }
}