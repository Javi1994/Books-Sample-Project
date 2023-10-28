package com.javi.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
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

    private var _user: User? = null
    var state by mutableStateOf(HomeUiState())

    init {
        viewModelScope.launch {
            getUserFromPreferencesUseCase.invoke()
                .collect { user ->
                    _user = user
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

            }
        }
    }

    private fun getFavouriteBooks() {
        viewModelScope.launch {
            getFavouriteBooksUseCase.invoke(_user?.username ?: "")
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
}