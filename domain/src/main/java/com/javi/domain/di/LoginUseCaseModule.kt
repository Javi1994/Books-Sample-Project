package com.javi.domain.di

import com.javi.data.di.repositoryModule
import com.javi.domain.use_case.login.LoginUseCase
import com.javi.domain.use_case.login.LoginWithTokenUseCase
import com.javi.domain.use_case.login.LogoutUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val loginUseCaseModule = module {
    includes(repositoryModule)
    includes(dispatcherModule)

    factoryOf(::LoginUseCase)
    factoryOf(::LoginWithTokenUseCase)
    factoryOf(::LogoutUseCase)
}
