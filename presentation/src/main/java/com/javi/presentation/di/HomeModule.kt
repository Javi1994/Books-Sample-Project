package com.javi.presentation.di

import com.javi.domain.di.booksUseCaseModule
import com.javi.domain.di.loginUseCaseModule
import com.javi.domain.di.preferencesUseCaseModule
import com.javi.domain.di.usersUseCaseModule
import com.javi.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    includes(booksUseCaseModule)
    includes(usersUseCaseModule)
    includes(preferencesUseCaseModule)
    includes(loginUseCaseModule)

    viewModelOf(::HomeViewModel)
}