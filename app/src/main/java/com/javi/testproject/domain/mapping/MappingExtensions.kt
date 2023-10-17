package com.javi.testproject.domain.mapping

import com.javi.testproject.data.dto.BookDetailDto
import com.javi.testproject.data.dto.BookDto
import com.javi.testproject.data.dto.UserDto
import com.javi.testproject.domain.model.Book
import com.javi.testproject.domain.model.BookDetail
import com.javi.testproject.domain.model.User

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