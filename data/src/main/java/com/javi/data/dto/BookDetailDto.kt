package com.javi.data.dto

data class BookDetailDto(
    val id: String,
    val title: String,
    val description: String,
    val pages: Int,
    val readTime: Int,
    val author: String
)