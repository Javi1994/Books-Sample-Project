package com.javi.presentation.di

import com.javi.presentation.login.LoginActivity
import org.koin.dsl.module

val activityModule = module {
    scope<LoginActivity> {
        scoped { "Hello" }
    }
}