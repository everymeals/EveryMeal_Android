package com.everymeal.presentation.ui.search.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealTextField

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    changeQuery: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onSearchAction: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_arrow_back_mono),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(12.dp)
                .clickable(onClick = onBackClick),
        )
        SearchBar(
            searchQuery = searchQuery,
            changeQuery = changeQuery,
            onSearchAction = onSearchAction
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    changeQuery: (String) -> Unit,
    onSearchAction: () -> Unit = {},
) {
    Box(modifier = modifier) {
        EveryMealTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            onValueChange = {
                changeQuery(it)
            },
            placeholderText = stringResource(R.string.placeholder_search),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_search_mono),
                    contentDescription = null,
                )
            },
            maxLines = 1,
            onEnterPressed = onSearchAction,
        )
    }
}
