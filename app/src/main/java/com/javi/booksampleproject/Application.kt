package com.javi.booksampleproject

import android.app.Application
import com.javi.data.di.dataSourceModule
import com.javi.data.di.databaseModule
import com.javi.data.di.networkModule
import com.javi.data.di.preferencesModule
import com.javi.data.di.repositoryModule
import com.javi.domain.di.booksUseCaseModule
import com.javi.domain.di.dispatcherModule
import com.javi.domain.di.loginUseCaseModule
import com.javi.domain.di.preferencesUseCaseModule
import com.javi.domain.di.usersUseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    preferencesModule,
                    dataSourceModule,
                    repositoryModule,
                    dispatcherModule,
                    booksUseCaseModule,
                    loginUseCaseModule,
                    preferencesUseCaseModule,
                    usersUseCaseModule
                )
            )
        }
    }
}