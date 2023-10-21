package com.javi.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.domain.use_case.book.GetFavouriteBooksUseCase
import com.javi.domain.use_case.login.LogoutUseCase
import com.javi.domain.use_case.user.GetUserUseCase
import com.javi.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val uiStateFavouriteBooks: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiStateUser: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    val logoutSuccess: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun getFavouriteBooks() {
        getFavouriteBooksUseCase.invoke("username")
            .map {
                UiState.Success(it)
            }
            .onEach {
                uiStateFavouriteBooks.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun getUser() {
        getUserUseCase.invoke()
            .map {
                UiState.Success(it)
            }
            .onEach {
                uiStateUser.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun logout() {
        logoutUseCase.invoke()
            .onEach {
                logoutSuccess.emit(true)
            }.launchIn(viewModelScope)
    }
}