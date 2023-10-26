package com.javi.domain.di

import com.javi.domain.use_case.login.LoginUseCase
import com.javi.domain.use_case.login.LoginWithTokenUseCase
import com.javi.domain.use_case.login.LogoutUseCase
import org.koin.dsl.module


val loginUseCaseModule = module {
    factory {
        LoginUseCase(get())
    }

    factory {
        LoginWithTokenUseCase(get())
    }

    factory {
        LogoutUseCase(get())
    }
}
