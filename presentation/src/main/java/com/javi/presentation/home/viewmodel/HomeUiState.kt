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
    val isLogoutLoading: Boolean = false,
    val logoutSuccess: Boolean = false,
    val error: Exception? = null,
) {
    val firstEntry: Boolean
        get() = !favouritesSelected && !allBooksSelected && !userSettingsSelected
}

sealed class HomeUiEvents {
    object GetFavouriteBooks : HomeUiEvents()
    object GetAllBooks : HomeUiEvents()
    object GetUserSettings : HomeUiEvents()
    object Logout : HomeUiEvents()
    data class OnBookClicked(val book: Book) : HomeUiEvents()
}