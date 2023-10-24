package com.javi.presentation.book_detail

import com.javi.domain.model.BookDetail

data class BookDetailUiState(
    val bookDetail: BookDetail? = null,
    val bookDetailId: String? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)

sealed class BookDetailUiEvents {
    object GetBookDetail : BookDetailUiEvents()
}
