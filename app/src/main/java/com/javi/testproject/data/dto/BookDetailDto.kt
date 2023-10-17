package com.javi.testproject.data.dto

import com.javi.testproject.domain.model.Book
import com.javi.testproject.domain.model.BookDetail

data class BookDetailDto(
    val id: String,
    val title: String,
    val description: String,
    val pages: Int,
    val readTime: Int,
    val author: String
)