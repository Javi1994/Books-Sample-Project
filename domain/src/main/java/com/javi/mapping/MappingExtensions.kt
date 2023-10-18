package com.javi.mapping

import com.javi.dto.BookDetailDto
import com.javi.dto.BookDto
import com.javi.dto.UserDto
import com.javi.model.Book
import com.javi.model.BookDetail
import com.javi.model.User


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