package com.javi.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.domain.model.Book
import com.javi.presentation.R
import com.javi.presentation.components.BookList
import com.javi.presentation.components.CustomErrorItem
import com.javi.presentation.components.CustomLoaderItem
import com.javi.presentation.components.EmptyDataItem

@Composable
fun AllBooksLayout(
    books: List<Book>,
    isLoading: Boolean = false,
    error: Exception? = null,
    onBookSelected: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        CustomLoaderItem(modifier = modifier)
    } else if (error != null) {
        CustomErrorItem(modifier = modifier)
    } else {
        if (books.isEmpty()) {
            EmptyDataItem(stringResource(id = R.string.books_empty_data))
        } else {
            AllBooksData(
                books = books,
                onBookSelected = onBookSelected,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun AllBooksData(
    books: List<Book>,
    onBookSelected: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BookList(books = books, onBookSelected)
    }
}


@Preview
@Composable
private fun AllBooksLayoutPreview() {
    AllBooksLayout(
        listOf(
            Book(),
            Book(),
            Book(),
            Book(),
            Book(),
        ),
        true,
        onBookSelected = {}
    )
}