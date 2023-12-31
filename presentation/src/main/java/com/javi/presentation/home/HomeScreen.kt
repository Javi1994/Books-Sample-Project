package com.javi.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.javi.domain.model.Book
import com.javi.presentation.ObserveAsEvents
import com.javi.presentation.R
import com.javi.presentation.components.BottomNav
import com.javi.presentation.components.TopNavBar
import com.javi.presentation.destinations.BookDetailScreenDestination
import com.javi.presentation.destinations.LoginScreenDestination
import com.javi.presentation.home.viewmodel.HomeNavigationEvent
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
    ObserveAsEvents(viewModel.navigationEventsChannelFlow) { event ->
        when (event) {
            is HomeNavigationEvent.NavigateToBookDetail -> {
                navigator.navigate(BookDetailScreenDestination)
            }

            is HomeNavigationEvent.NavigateToLogin -> {
                navigator.navigate(LoginScreenDestination)
            }
        }
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

@OptIn(ExperimentalMaterial3Api::class)
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
                error = state.error,
                onBookSelected = onBookSelected,
                modifier = Modifier.padding(innerPadding)
            )
        } else if (state.userSettingsSelected) {
            UserSettingsLayout(
                user = state.user,
                error = state.error,
                isLoading = state.isLoading,
                isLogoutLoading = state.isLogoutLoading,
                onLogoutClick = {
                    onLogoutClick()
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            FavouriteBooksLayout(
                books = emptyList(),
                error = state.error,
                isLoading = state.isLoading,
                onBookSelected = onBookSelected,
                modifier = Modifier.padding(innerPadding)
            )
        }

        //TODO: Check if there is a better way to handle no back navigation in this screen
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