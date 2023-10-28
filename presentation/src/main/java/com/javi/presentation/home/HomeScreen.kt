package com.javi.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.javi.presentation.components.BottomNav
import com.javi.presentation.home.viewmodel.HomeUiEvents
import com.javi.presentation.home.viewmodel.HomeUiState
import com.javi.presentation.home.viewmodel.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = true)
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = koinViewModel()
) {
    HomeLayout(
        state = viewModel.state,
        onFavouriteClick = {
            viewModel.onEvent(HomeUiEvents.GetFavouriteBooks)
        },
        onBooksClick = {
            viewModel.onEvent(HomeUiEvents.GetAllBooks)
        },
        onUserClick = {
            viewModel.onEvent(HomeUiEvents.GetUserSettings)
        }
    )
}

@Composable
fun HomeLayout(
    state: HomeUiState,
    onFavouriteClick: () -> Unit,
    onBooksClick: () -> Unit,
    onUserClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        BottomNav(
            modifier = Modifier.align(Alignment.BottomStart),
            favouritesSelected = state.favouritesSelected,
            allBooksSelected = state.allBooksSelected,
            userSettingsSelected = state.userSettingsSelected,
            onFavouriteClick = onFavouriteClick,
            onBooksClick = onBooksClick,
            onUserClick = onUserClick
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeLayout(
        HomeUiState(),
        {}, {}, {}
    )
}