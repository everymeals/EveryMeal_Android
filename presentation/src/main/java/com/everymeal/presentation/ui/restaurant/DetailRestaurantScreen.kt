package com.everymeal.presentation.ui.restaurant

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray900
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailRestaurantScreen(

) {
    val pages = listOf("Page 0", "Page 1", "Page 2")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedTabIndex = page
        }
    }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Gray900,
                )
            },
            divider = { Divider(color = Gray500) },
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = Gray900,
                    unselectedContentColor = Gray500,
                )
            }
        }

        HorizontalPager(state = pagerState) { page ->
            Text(
                text = "$page",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}