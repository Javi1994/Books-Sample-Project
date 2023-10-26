package com.javi.presentation.di

import com.javi.presentation.book_detail.viewmodel.BookDetailViewModel
import com.javi.presentation.home.viewmodel.HomeViewModel
import com.javi.presentation.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::BookDetailViewModel)
}