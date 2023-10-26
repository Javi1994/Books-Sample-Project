package com.javi.domain.di

import com.javi.domain.use_case.preferences.GetUserFromPreferencesUseCase
import org.koin.dsl.module


val preferencesUseCaseModule = module {
    factory {
        GetUserFromPreferencesUseCase(get())
    }
}
