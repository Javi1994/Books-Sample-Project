package com.javi.data.dto

data class BookDetailDto(
    val id: String = "Id",
    val title: String = "Title",
    val description: String = "Description",
    val pages: Int = 0,
    val readTime: Int = 0,
    val author: String = "Author"
)