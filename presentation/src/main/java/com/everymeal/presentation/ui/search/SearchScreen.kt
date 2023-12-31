package com.everymeal.presentation.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.search.history.SearchHistoryList
import com.everymeal.presentation.ui.search.topbar.TopBar
import com.everymeal.presentation.ui.theme.Gray800

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    Scaffold(
        topBar = {
            TopBar(
                onBackClick = { },
                setShowHistory = { viewModel.setEvent(SearchEvent.SetShowSearchHistory(it)) },
                searchQuery = viewState.value.searchQuery,
                changeQuery = { viewModel.setEvent(SearchEvent.SearchQueryChanged(it)) },
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        SearchDetail(
            modifier = Modifier.padding(innerPadding)
        )
        if (viewState.value.searchIsShowHistory) {
            SearchHistoryList(
                historyItems = viewState.value.searchHistoryItems,
                isVisible = true,
                onHistoryItemClicked = { item ->
                    viewModel.setEvent(SearchEvent.SearchQueryChanged(item))
                },
                removeHistoryItem = { removeItem ->
                    removeHistoryItem(viewState, removeItem, viewModel)
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        } else {
            EmptyView()
        }
    }
}

@Composable
fun SearchDetail(
    modifier: Modifier = Modifier
) {

}

@Composable
fun EmptyView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_store),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.heightIn(8.dp))
            Text(
                text = stringResource(R.string.empty_search),
                style = TextStyle(
                    color = Gray800,
                    fontWeight = FontWeight(500),
                    fontSize = 15.sp
                )
            )
        }
    }
}

private fun removeHistoryItem(
    viewState: State<SearchState>,
    removeItem: String,
    viewModel: SearchViewModel
) {
    val historyItems = viewState.value.searchHistoryItems
    val removedHistoryItems = historyItems.filterNot { it == removeItem }
    viewModel.setEvent(SearchEvent.UpdateSearchHistory(removedHistoryItems))
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}
