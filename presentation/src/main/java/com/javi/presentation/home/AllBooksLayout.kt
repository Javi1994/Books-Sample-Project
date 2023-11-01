package com.javi.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.domain.model.Book
import com.javi.presentation.components.BookList

@Composable
fun AllBooksLayout(
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
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
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