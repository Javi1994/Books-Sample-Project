package com.javi.presentation.home.viewmodel

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserFromPreferencesUseCase: GetUserFromPreferencesUseCase,
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private var _user: User? = null

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> =
        _homeUiState.asStateFlow()

    private val _favouriteBooksUiState = MutableStateFlow(FavouriteBooksUiState())
    val favouriteBooksUiState: StateFlow<FavouriteBooksUiState> =
        _favouriteBooksUiState.asStateFlow()

    private val _allBooksUiState = MutableStateFlow(AllBooksUiState())
    val allBooksUiState: StateFlow<AllBooksUiState> =
        _allBooksUiState.asStateFlow()

    private val _userSettingsUiState = MutableStateFlow(UserSettingsUiState())
    val userSettingsUiState: StateFlow<UserSettingsUiState> =
        _userSettingsUiState.asStateFlow()

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
                _homeUiState.update {
                    it.copy(
                        favouritesSelected = true,
                        allBooksSelected = false,
                        userSettingsSelected = false
                    )
                }
            }

            is HomeUiEvents.OnBookClicked -> {
                selectBook(event.book)
            }

            is HomeUiEvents.GetAllBooks -> {
                getAllBooks()
                _homeUiState.update {
                    it.copy(
                        favouritesSelected = false,
                        allBooksSelected = true,
                        userSettingsSelected = false
                    )
                }
            }

            is HomeUiEvents.GetUserSettings -> {
                getUserSettings()
                _homeUiState.update {
                    it.copy(
                        favouritesSelected = false,
                        allBooksSelected = false,
                        userSettingsSelected = true
                    )
                }
            }

            is HomeUiEvents.Logout -> {
                logout()
            }
        }
    }

    private fun getFavouriteBooks() {
        if (favouriteBooksUiState.value.hasBooks) { return }

        viewModelScope.launch {
            getFavouriteBooksUseCase.invoke(_user?.username ?: "")
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                _favouriteBooksUiState.update {
                                    it.copy(
                                        books = books,
                                        isLoading = result.isLoading,
                                        error = result.error
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _favouriteBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }

                        is Resource.Error -> {
                            _favouriteBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun getAllBooks() {
        if (allBooksUiState.value.hasBooks) { return }

        viewModelScope.launch {
            getAllBooksUseCase.invoke()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                _allBooksUiState.update {
                                    it.copy(
                                        books = books,
                                        isLoading = result.isLoading,
                                        error = result.error
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _allBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }

                        is Resource.Error -> {
                            _allBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun getUserSettings() {
        if (userSettingsUiState.value.user != null) { return }

        viewModelScope.launch {
            getUserUseCase.invoke()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { user ->
                                _userSettingsUiState.update {
                                    it.copy(
                                        user = user,
                                        isLoading = result.isLoading,
                                        error = result.error
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _userSettingsUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }

                        is Resource.Error -> {
                            _userSettingsUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }
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
                            _userSettingsUiState.update {
                                it.copy(
                                    logoutSuccess = true,
                                    isLogoutLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _userSettingsUiState.update {
                                it.copy(
                                    isLogoutLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }

                        is Resource.Error -> {
                            _userSettingsUiState.update {
                                it.copy(
                                    isLogoutLoading = result.isLoading,
                                    error = result.error
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun selectBook(book: Book) {
        _favouriteBooksUiState.update {
            it.copy(
                selectedBook = book
            )
        }
    }

    fun bookWasSelected() {
        _favouriteBooksUiState.update {
            it.copy(
                selectedBook = null
            )
        }
    }
}