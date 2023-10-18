package com.javi.booksampleproject.domain.mapping

import com.javi.booksampleproject.data.dto.BookDetailDto
import com.javi.booksampleproject.data.dto.BookDto
import com.javi.booksampleproject.data.dto.UserDto
import com.javi.booksampleproject.domain.model.Book
import com.javi.booksampleproject.domain.model.BookDetail
import com.javi.booksampleproject.domain.model.User


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