package com.javi.testproject.di

import com.javi.testproject.data.LoginRepositoryImpl
import com.javi.testproject.data.mock.LoginMock
import com.javi.testproject.domain.use_case.LoginUseCase
import com.javi.testproject.domain.use_case.LoginWithTokenUseCase

object AppModule {

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

    fun provideLoginRepository() = mockLoginRepository
    fun provideLoginUseCase() = loginUseCase
    fun provideLoginWithTokenUseCase() = loginWithTokenUseCase
}