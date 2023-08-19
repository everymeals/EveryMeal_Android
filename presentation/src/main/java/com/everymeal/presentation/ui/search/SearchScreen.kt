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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.search.history.SearchHistoryList
import com.everymeal.presentation.ui.search.topbar.TopBar
import com.everymeal.presentation.ui.theme.Gray800

@Composable
fun SearchScreen() {
    var showHistory by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopBar(
                onBackClick = { },
                setShowHistory = { showHistory = it }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        SearchDetail(
            modifier = Modifier.padding(innerPadding)
        )
        if (showHistory) {
            SearchHistoryList(
                historyItems = remember {
                    mutableStateOf(listOf("테스트", "테스트2", "테스트3"))
                },
                isVisible = true,
                onHistoryItemClicked = { item ->

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


@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}