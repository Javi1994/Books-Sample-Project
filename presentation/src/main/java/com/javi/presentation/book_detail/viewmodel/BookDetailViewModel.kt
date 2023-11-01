package com.javi.presentation.book_detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.domain.use_case.book.GetBookDetailUseCase
import kotlinx.coroutines.launch

class BookDetailViewModel constructor(
    private val getBookDetail: GetBookDetailUseCase
) : ViewModel() {

    var state by mutableStateOf(BookDetailUiState())

    init {
        getBookDetail()
    }

    private fun getBookDetail() {
        viewModelScope.launch {
            getBookDetail.invoke(state.bookDetailId ?: "")
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { books ->
                                state = state.copy(
                                    bookDetail = books,
                                    isLoading = false,
                                )
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true,
                            )
                        }
                        is Resource.Error -> {}
                    }
                }
        }
    }
}