package com.javi.testproject.data.remote.dto

data class BookDetailDto(
    private val id: String,
    private val name: String,
    private val description: String,
    private val pages: Int,
    private val readTime: Int,
    private val author: String
)