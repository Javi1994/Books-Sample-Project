package com.javi.presentation.book_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javi.domain.model.BookDetail
import com.javi.presentation.R
import com.javi.presentation.book_detail.viewmodel.BookDetailUiState
import com.javi.presentation.book_detail.viewmodel.BookDetailViewModel
import com.javi.presentation.components.CustomErrorItem
import com.javi.presentation.components.CustomLoaderItem
import com.javi.presentation.components.TopNavBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = false)
fun BookDetailScreen(
    navigator: DestinationsNavigator,
    viewModel: BookDetailViewModel = koinViewModel()
) {
    BookDetailLayout(
        state = viewModel.state,
        onBack = { navigator.popBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailLayout(
    state: BookDetailUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopNavBar(
                title = stringResource(id = R.string.book_detail_toolbar_title),
                withBackNavigation = true,
                onBack = {
                    onBack()
                }
            )
        },
    ) { innerPadding ->
        if (state.isLoading) {
            CustomLoaderItem(modifier = Modifier.padding(innerPadding))
        } else if (state.error != null) {
            CustomErrorItem(modifier = Modifier.padding(innerPadding))
        } else {
            BookDetailData(
                modifier = Modifier.padding(innerPadding),
                state = state
            )
        }
    }
}

@Composable
fun BookDetailData(
    modifier: Modifier = Modifier,
    state: BookDetailUiState,
) {
    state.bookDetail?.let { bookDetail ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = bookDetail.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = bookDetail.author,
                    textAlign = TextAlign.End,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = bookDetail.description,
                fontSize = 18.sp,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontSize = 20.sp,
                color = Color.Black,
                text = stringResource(
                    id = R.string.book_read_time,
                    bookDetail.readTime
                ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                fontSize = 20.sp,
                color = Color.Gray,
                text = stringResource(id = R.string.book_pages, bookDetail.pages),
            )
        }
    }
}

@Preview
@Composable
fun BookDetailScreenPreview() {
    BookDetailLayout(
        state = BookDetailUiState(
            bookDetail = BookDetail(
                "Default title",
                "Default description", 350,
                65,
                "Me",
            ),
        ), onBack = {}
    )
}