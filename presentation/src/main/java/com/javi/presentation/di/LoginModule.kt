package com.javi.presentation.di

import com.javi.domain.di.loginUseCaseModule
import com.javi.domain.di.preferencesUseCaseModule
import com.javi.presentation.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    includes(loginUseCaseModule)
    includes(preferencesUseCaseModule)
    includes(loginUseCaseModule)

    viewModelOf(::LoginViewModel)
}