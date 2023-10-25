package com.javi.presentation.book_detail.viewmodel

import com.javi.domain.model.BookDetail

data class BookDetailUiState(
    val bookDetail: BookDetail? = null,
    val bookDetailId: String? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null
)

sealed class BookDetailUiEvents {
    object GetBookDetail : BookDetailUiEvents()
}
