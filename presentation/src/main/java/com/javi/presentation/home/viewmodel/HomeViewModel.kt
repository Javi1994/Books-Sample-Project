package com.javi.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.common.hasError
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

    private val _favouriteBooksUiState = MutableStateFlow(FavouriteBooksUiState())
    val favouriteBooksUiState: StateFlow<FavouriteBooksUiState> =
        _favouriteBooksUiState.asStateFlow()

    private val _allBooksUiState = MutableStateFlow(AllBooksUiState())
    val allBooksUiState: StateFlow<AllBooksUiState> =
        _allBooksUiState.asStateFlow()

    private val _userSettingsUiState = MutableStateFlow(UserSettingsUiState())
    val userSettingsUiState: StateFlow<UserSettingsUiState> =
        _userSettingsUiState.asStateFlow()

    fun onEvent(event: HomeUiEvents) {
        when (event) {
            is HomeUiEvents.GetFavouriteBooks -> { getFavouriteBooks() }
            is HomeUiEvents.OnBookClicked -> { }
            is HomeUiEvents.GetAllBooks -> { getAllBooks() }
            is HomeUiEvents.GetUserSettings -> { getUserSettings() }
            is HomeUiEvents.Logout -> { }
        }
    }

    init {
        viewModelScope.launch {
            getUserFromPreferencesUseCase.invoke()
                .collect { user ->
                    _user = user
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
                                _favouriteBooksUiState.update {
                                    it.copy(
                                        books = books,
                                        isLoading = result.isLoading,
                                        error = result.hasError
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _favouriteBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.hasError
                                )
                            }
                        }

                        is Resource.Error -> {
                            _favouriteBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.hasError
                                )
                            }
                        }
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
                                _allBooksUiState.update {
                                    it.copy(
                                        books = books,
                                        isLoading = result.isLoading,
                                        error = result.hasError
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _allBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.hasError
                                )
                            }
                        }

                        is Resource.Error -> {
                            _allBooksUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.hasError
                                )
                            }
                        }
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
                                _userSettingsUiState.update {
                                    it.copy(
                                        user = user,
                                        isLoading = result.isLoading,
                                        error = result.hasError
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _userSettingsUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.hasError
                                )
                            }
                        }

                        is Resource.Error -> {
                            _userSettingsUiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.hasError
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun logout() {

    }
}