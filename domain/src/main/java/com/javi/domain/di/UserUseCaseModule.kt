package com.javi.domain.di

import com.javi.domain.use_case.user.GetUserUseCase
import org.koin.dsl.module


val usersUseCaseModule = module {
    factory {
        GetUserUseCase(get())
    }
}
