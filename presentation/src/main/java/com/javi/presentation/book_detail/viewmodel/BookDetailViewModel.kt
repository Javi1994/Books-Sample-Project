package com.javi.presentation.book_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.domain.use_case.book.GetBookDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetail: GetBookDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState: StateFlow<BookDetailUiState> =
        _uiState.asStateFlow()

    fun onEvent(event: BookDetailUiEvents) {
        when (event) {
            is BookDetailUiEvents.GetBookDetail -> {
                getBookDetail()
            }
        }
    }

    private fun getBookDetail() {
        viewModelScope.launch {
            getBookDetail.invoke(uiState.value.bookDetailId ?: "")
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                _uiState.update {
                                    it.copy(
                                        bookDetail = books,
                                        isLoading = result.isLoading,
                                        error = result.hasError
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = result.isLoading,
                                    error = result.hasError
                                )
                            }
                        }

                        is Resource.Error -> {
                            _uiState.update {
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
}