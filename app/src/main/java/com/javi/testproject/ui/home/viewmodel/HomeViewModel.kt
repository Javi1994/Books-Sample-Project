package com.javi.testproject.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.testproject.common.UiState
import com.javi.testproject.di.AppModule
import com.javi.testproject.domain.use_case.GetFavouriteBooksUseCase
import com.javi.testproject.domain.use_case.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase = AppModule.provideGetFavouriteBooksUseCase()
) : ViewModel() {

    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    fun getFavouriteBooks() {

    }
}