package com.everymeal.presentation.ui.search.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealTextField

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    changeQuery: (String) -> Unit,
    onBackClick: () -> Unit,
    setShowHistory: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = modifier
                .size(48.dp)
                .padding(12.dp)
                .clickable {
                    onBackClick()
                }
        )
        SearchBar(
            searchQuery = searchQuery,
            changeQuery = changeQuery,
            setShowHistory = setShowHistory,
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    changeQuery: (String) -> Unit,
    setShowHistory: (Boolean) -> Unit
) {
    Box(modifier = modifier) {
        EveryMealTextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            value = searchQuery,
            onValueChange = {
                changeQuery(it)
                setShowHistory(it.isEmpty())
            },
            placeholderText = stringResource(R.string.placeholder_search),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_search_mono),
                    contentDescription = null
                )
            },
        )
    }
}
