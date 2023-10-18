package com.javi.presentation.common

sealed class UiState {
    object Loading: UiState()
    data class Success<T>(val data: T): UiState()
    data class Error(private val error: String): UiState()
}