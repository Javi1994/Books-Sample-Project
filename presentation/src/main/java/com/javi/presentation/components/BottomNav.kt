package com.javi.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javi.presentation.R

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    favouritesSelected: Boolean = false,
    allBooksSelected: Boolean = false,
    userSettingsSelected: Boolean = false,
    onFavouriteClick: () -> Unit,
    onBooksClick: () -> Unit,
    onUserClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Divider(
            color = Color.DarkGray,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            SelectableText(
                value = stringResource(id = R.string.bottom_nav_favourites),
                selected = favouritesSelected,
                modifier = Modifier
                    .weight(1f),
                onClick = onFavouriteClick
            )
            VerticalDivider()
            SelectableText(
                value = stringResource(id = R.string.bottom_nav_all_books),
                selected = allBooksSelected,
                modifier = Modifier
                    .weight(1f),
                onClick = onBooksClick
            )
            VerticalDivider()
            SelectableText(
                value = stringResource(id = R.string.bottom_nav_user_settings),
                selected = userSettingsSelected,
                modifier = Modifier
                    .weight(1f),
                onClick = onUserClick
            )
        }
    }
}

@Composable
private fun SelectableText(
    value: String,
    selected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    if (selected) {
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = modifier
                .fillMaxSize()
                .clickable {}
                .wrapContentHeight(Alignment.CenterVertically),
        )
    } else {
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontSize = 20.sp,
            modifier = modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                }
                .wrapContentHeight(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun BottomNavPreview() {
    BottomNav(onFavouriteClick = {}, onBooksClick = {}, onUserClick = {})
}