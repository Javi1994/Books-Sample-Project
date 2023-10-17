package com.javi.testproject.data.dto

import com.javi.testproject.domain.model.Book
import com.javi.testproject.domain.model.User

data class BookDto(
    val id: String = "Default Id",
    val title: String = "Default Name",
    val description: String = "Default Description",
    val author: String = "Default Author"
)