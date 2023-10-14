package com.javi.testproject.di

import com.javi.testproject.data.BookRepositoryImpl
import com.javi.testproject.data.LoginRepositoryImpl
import com.javi.testproject.data.UserRepositoryImpl
import com.javi.testproject.data.mock.BookMock
import com.javi.testproject.data.mock.LoginMock
import com.javi.testproject.data.mock.UserMock
import com.javi.testproject.domain.use_case.GetBookDetailUseCase
import com.javi.testproject.domain.use_case.GetFavouriteBooksUseCase
import com.javi.testproject.domain.use_case.LoginUseCase
import com.javi.testproject.domain.use_case.LoginWithTokenUseCase
import com.javi.testproject.domain.use_case.LogoutUseCase

object AppModule {

    private val mockBookRepository by lazy {
        BookRepositoryImpl(
            BookMock()
        )
    }

    private val getFavouriteBooksUseCase by lazy {
        GetFavouriteBooksUseCase(mockBookRepository)
    }

    private val getBookDetailUseCase by lazy {
        GetBookDetailUseCase(mockBookRepository)
    }

    fun provideGetFavouriteBooksUseCase() = getFavouriteBooksUseCase
    fun provideGetBookDetailUseCase() = getBookDetailUseCase

    private val userRepository by lazy {
        UserRepositoryImpl(
            UserMock()
        )
    }

    private val logoutUseCase by lazy {
        LogoutUseCase(userRepository)
    }

    fun provideLogoutUseCase() = logoutUseCase


    private val mockLoginRepository by lazy {
        LoginRepositoryImpl(
            LoginMock()
        )
    }

    private val loginUseCase by lazy {
        LoginUseCase(mockLoginRepository)
    }

    private val loginWithTokenUseCase by lazy {
        LoginWithTokenUseCase(mockLoginRepository)
    }

    fun provideLoginUseCase() = loginUseCase
    fun provideLoginWithTokenUseCase() = loginWithTokenUseCase
}