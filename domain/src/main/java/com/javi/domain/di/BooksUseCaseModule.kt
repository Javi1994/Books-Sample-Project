package com.javi.domain.di

import com.javi.domain.use_case.book.GetAllBooksUseCase
import com.javi.domain.use_case.book.GetBookDetailUseCase
import com.javi.domain.use_case.book.GetFavouriteBooksUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val booksUseCaseModule = module {
    factoryOf(::GetAllBooksUseCase)
    factoryOf(::GetBookDetailUseCase)
    factoryOf(::GetFavouriteBooksUseCase)
}
