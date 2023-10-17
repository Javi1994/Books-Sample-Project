package com.javi.testproject.ui.book_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.testproject.common.UiState
import com.javi.testproject.di.AppModule
import com.javi.testproject.domain.use_case.GetBookDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class BookDetailViewModel(
    private val getBookDetail: GetBookDetailUseCase = AppModule.provideGetBookDetailUseCase()
) : ViewModel() {

    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    fun getBookDetail() {
        getBookDetail.invoke("book_id")
            .map {
                println("Mapping login result to uiState: $it")
                UiState.Success(it)
            }
            .onEach {
                println("Emitting uiState: $it")
                uiState.emit(it)
            }
            .launchIn(viewModelScope)
    }
}