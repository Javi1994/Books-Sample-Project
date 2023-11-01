package com.javi.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.javi.domain.model.Book
import com.javi.presentation.R
import com.javi.presentation.components.BottomNav
import com.javi.presentation.components.TopNavBar
import com.javi.presentation.destinations.BookDetailScreenDestination
import com.javi.presentation.destinations.LoginScreenDestination
import com.javi.presentation.home.viewmodel.HomeUiEvents
import com.javi.presentation.home.viewmodel.HomeUiState
import com.javi.presentation.home.viewmodel.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination(start = false)
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = koinViewModel()
) {

    if (viewModel.state.logoutSuccess) {
        navigator.navigate(LoginScreenDestination)
    }
    if (viewModel.state.selectedBook != null) {
        viewModel.onEvent(HomeUiEvents.NavigateToBookDetail)
        navigator.navigate(BookDetailScreenDestination)
    }
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
        },
        onBookSelected = {
            viewModel.onEvent(HomeUiEvents.OnBookClicked(it))
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
    onBookSelected: (Book) -> Unit,
) {
    Scaffold(
        topBar = {
            TopNavBar(
                title = stringResource(id = R.string.home_toolbar_title),
                onBack = {}
            )
        },
        bottomBar = {
            BottomNav(
                favouritesSelected = state.favouritesSelected,
                allBooksSelected = state.allBooksSelected,
                userSettingsSelected = state.userSettingsSelected,
                onFavouriteClick = onFavouriteClick,
                onBooksClick = onBooksClick,
                onUserClick = onUserClick
            )
        },
    ) { innerPadding ->

        if (state.allBooksSelected) {
            AllBooksLayout(
                books = state.allBooks,
                isLoading = state.isLoading,
                onBookSelected = onBookSelected,
                modifier = Modifier.padding(innerPadding)
            )
        } else if (state.userSettingsSelected) {
            UserSettingsLayout(
                user = state.user,
                isLoading = state.isLoading,
                isLogoutLoading = state.isLogoutLoading,
                onLogoutClick = {
                    onLogoutClick()
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            FavouriteBooksLayout(
                books = state.favouriteBooks,
                isLoading = state.isLoading,
                onBookSelected = onBookSelected,
                modifier = Modifier.padding(innerPadding)
            )
        }

        BackHandler {}
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeLayout(
        HomeUiState(),
        {}, {}, {}, {},
        onBookSelected = {}
    )
}