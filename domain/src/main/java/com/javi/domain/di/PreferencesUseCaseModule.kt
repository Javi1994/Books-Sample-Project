package com.javi.domain.di

import com.javi.data.di.repositoryModule
import com.javi.domain.use_case.preferences.GetUserFromPreferencesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val preferencesUseCaseModule = module {
    includes(repositoryModule)
    includes(dispatcherModule)

    factoryOf(::GetUserFromPreferencesUseCase)
}
