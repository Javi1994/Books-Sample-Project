package com.javi.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.domain.model.Book

@Composable
fun BookList(
    books: List<Book>,
    onBookSelected: (Book) -> Unit,
) {
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(books) {
            BookItem(book = it, modifier = Modifier.clickable {
                onBookSelected(it)
            })
        }
    }
}

@Preview
@Composable
private fun BookListPreview() {
    BookList(
        listOf(
            Book(),
            Book(),
            Book(),
            Book(),
            Book(),
        ), onBookSelected = {

        }
    )
}