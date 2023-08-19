package com.everymeal.presentation.ui.search.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.search.history.SearchHistoryList
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500

@Composable
fun TopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = modifier
                .size(width = 48.dp, height = 48.dp)
                .padding(12.dp)
                .clickable {
                    onBackClick()
                }
        )
        SearchBar(
            initialSearchHistory = listOf("테스트", "테스트2", "테스트3"),
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    initialSearchHistory: List<String> = listOf()
) {
    var searchQuery by remember { mutableStateOf("") }

    val searchHistory = remember { mutableStateOf(initialSearchHistory) }
    val showHistory = remember { mutableStateOf(true) }

    Box(modifier = modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                showHistory.value = it.isEmpty()
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_search_mono),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_search),
                    style = TextStyle(
                        color = Gray500,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp
                    )
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Gray300,
                unfocusedContainerColor = Gray300,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
        if (showHistory.value) {
            SearchHistoryList(
                historyItems = searchHistory,
                isVisible = true,
                onHistoryItemClicked = { item ->
                    searchQuery = item
                    showHistory.value = false
                },
                modifier = Modifier.padding(top = 56.dp)
            )
        }
    }
}