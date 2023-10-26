package com.javi.presentation.di

import org.koin.dsl.module

val appModule = module {
    includes(loginModule)
    includes(homeModule)
    includes(booksModule)
}