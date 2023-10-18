package com.javi.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.domain.use_case.GetFavouriteBooksUseCase
import com.javi.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    fun getFavouriteBooks() {
        getFavouriteBooksUseCase.invoke("username")
            .map {
                val uiState = UiState.Success(it)
                println("Mapping favourite books result to uiState: $uiState")
                uiState
            }
            .onEach {
                println("Emitting uiState: $it")
                uiState.emit(it)
            }
            .launchIn(viewModelScope)
    }
}