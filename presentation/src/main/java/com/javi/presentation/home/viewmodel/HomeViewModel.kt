package com.javi.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.domain.model.Book
import com.javi.domain.model.User
import com.javi.domain.use_case.book.GetAllBooksUseCase
import com.javi.domain.use_case.book.GetFavouriteBooksUseCase
import com.javi.domain.use_case.login.LogoutUseCase
import com.javi.domain.use_case.preferences.GetUserFromPreferencesUseCase
import com.javi.domain.use_case.user.GetUserUseCase
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val getUserFromPreferencesUseCase: GetUserFromPreferencesUseCase,
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private var _userFromPreferences: User? = null
    var state by mutableStateOf(HomeUiState())

    init {
        viewModelScope.launch {
            getUserFromPreferencesUseCase.invoke()
                .collect { user ->
                    _userFromPreferences = user
                }
        }
    }

    fun onEvent(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.GetFavouriteBooks -> {
                getFavouriteBooks()
                state = state.copy(
                    favouritesSelected = true,
                    allBooksSelected = false,
                    userSettingsSelected = false
                )
            }

            is HomeUiEvents.OnBookClicked -> {
                selectBook(event.book)
            }
            is HomeUiEvents.NavigateToBookDetail -> {
                bookWasSelected()
            }

            is HomeUiEvents.GetAllBooks -> {
                getAllBooks()
                state = state.copy(
                    favouritesSelected = false,
                    allBooksSelected = true,
                    userSettingsSelected = false
                )
            }

            is HomeUiEvents.GetUserSettings -> {
                getUserSettings()
                state = state.copy(
                    favouritesSelected = false,
                    allBooksSelected = false,
                    userSettingsSelected = true
                )
            }

            is HomeUiEvents.Logout -> {
                logout()
            }
        }
    }

    private fun getFavouriteBooks() {
        if (state.favouriteBooks.isNotEmpty()) {
            return
        }

        viewModelScope.launch {
            getFavouriteBooksUseCase.invoke(_userFromPreferences?.username ?: "")
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    favouriteBooks = books,
                                    isLoading = false
                                )
                            }
                        }

                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }

                        is Resource.Error -> {}
                    }
                }
        }
    }

    private fun getAllBooks() {
        if (state.allBooks.isNotEmpty()) {
            return
        }

        viewModelScope.launch {
            getAllBooksUseCase.invoke()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    allBooks = books,
                                    isLoading = false
                                )
                            }
                        }

                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }

                        is Resource.Error -> {}
                    }
                }
        }
    }

    private fun getUserSettings() {
        if (state.user != null) {
            return
        }

        viewModelScope.launch {
            getUserUseCase.invoke()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { user ->
                                state = state.copy(
                                    user = user,
                                    isLoading = false
                                )
                            }
                        }

                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }

                        is Resource.Error -> {}
                    }
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { user ->
                                state = state.copy(
                                    logoutSuccess = true,
                                    isLogoutLoading = false,
                                )
                            }
                        }

                        is Resource.Loading -> {
                            state = state.copy(
                                isLogoutLoading = true,
                            )
                        }

                        is Resource.Error -> {}
                    }
                }
        }
    }

    private fun selectBook(book: Book) {
        state = state.copy(
            selectedBook = book
        )
    }

    private fun bookWasSelected() {
        state = state.copy(
            selectedBook = null
        )
    }
}