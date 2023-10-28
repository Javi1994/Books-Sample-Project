package com.javi.presentation.home.viewmodel

import com.javi.domain.model.Book
import com.javi.domain.model.User

data class HomeUiState(
    val favouritesSelected: Boolean = false,
    val allBooksSelected: Boolean = false,
    val userSettingsSelected: Boolean = false,
    val favouriteBooks: List<Book> = listOf(),
    val allBooks: List<Book> = listOf(),
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null,
) {
    val firstEntry: Boolean
        get() = !favouritesSelected && !allBooksSelected && !userSettingsSelected
}

data class FavouriteBooksUiState(
    val books: List<Book> = listOf(),
    val selectedBook: Book? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null
) {
    val hasBooks: Boolean
        get() = books.isNotEmpty()
}

data class AllBooksUiState(
    val books: List<Book> = listOf(),
    val selectedBook: Book? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null
) {
    val hasBooks: Boolean
        get() = books.isNotEmpty()
}

data class UserSettingsUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val logoutSuccess: Boolean = false,
    val isLogoutLoading: Boolean = false
)

sealed class HomeUiEvents {
    object GetFavouriteBooks : HomeUiEvents()
    object GetAllBooks : HomeUiEvents()
    object GetUserSettings : HomeUiEvents()
    object Logout : HomeUiEvents()
    data class OnBookClicked(val book: Book) : HomeUiEvents()
}