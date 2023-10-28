package com.javi.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    if (viewModel.state.firstEntry) {
        viewModel.onEvent(HomeUiEvents.GetFavouriteBooks)
    }
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
        },
        onLogoutClick = {
            viewModel.onEvent(HomeUiEvents.Logout)
        }
    )
}

@Composable
fun HomeLayout(
    state: HomeUiState,
    onFavouriteClick: () -> Unit,
    onBooksClick: () -> Unit,
    onUserClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        if (state.allBooksSelected) {
            AllBooksLayout(
                books = state.allBooks,
                isLoading = state.isLoading,
            )
        } else if (state.userSettingsSelected) {
            UserSettingsLayout(
                user = state.user,
                isLoading = state.isLoading,
                isLogoutLoading = state.isLogoutLoading,
                onLogoutClick = {
                    onLogoutClick()
                }
            )
        } else {
            FavouriteBooksLayout(
                books = state.favouriteBooks,
                isLoading = state.isLoading
            )
        }

        BottomNav(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(Color.White),
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
        {}, {}, {}, {}
    )
}