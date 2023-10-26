package com.javi.domain.di

import com.javi.data.di.repositoryModule
import com.javi.domain.use_case.user.GetUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val usersUseCaseModule = module {
    includes(repositoryModule)
    includes(dispatcherModule)

    factoryOf(::GetUserUseCase)
}
