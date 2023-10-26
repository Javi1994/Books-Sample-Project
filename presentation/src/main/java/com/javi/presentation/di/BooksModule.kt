package com.javi.presentation.di

import com.javi.domain.di.booksUseCaseModule
import com.javi.presentation.book_detail.viewmodel.BookDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val booksModule = module {
    includes(booksUseCaseModule)

    viewModelOf(::BookDetailViewModel)
}