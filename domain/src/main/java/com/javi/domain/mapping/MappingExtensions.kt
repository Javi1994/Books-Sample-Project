package com.javi.domain.mapping

import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import com.javi.data.dto.UserDto
import com.javi.domain.model.Book
import com.javi.domain.model.BookDetail
import com.javi.domain.model.User


fun UserDto.toUser(): User {
    return User(
        username = this.username,
        token = this.token
    )
}

fun BookDto.toBook(): Book {
    return Book(
        title = this.title,
        description = this.description,
        author = this.author,
    )
}

fun BookDetailDto.toBookDetail(): BookDetail {
    return BookDetail(
        title = this.title,
        description = this.description,
        pages = this.pages,
        readTime = this.readTime,
        author = this.author,
    )
}