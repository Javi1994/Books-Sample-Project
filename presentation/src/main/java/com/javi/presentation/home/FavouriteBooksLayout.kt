package com.javi.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.domain.model.Book
import com.javi.presentation.components.BookList
import com.javi.presentation.components.CustomLoaderItem

@Composable
fun FavouriteBooksLayout(
    books: List<Book>,
    isLoading: Boolean = false,
    onBookSelected: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (!isLoading) {
            BookList(books = books, onBookSelected)
        } else {
            CustomLoaderItem()
        }
    }
}


@Preview
@Composable
private fun FavouriteBooksLayoutPreview() {
    FavouriteBooksLayout(
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