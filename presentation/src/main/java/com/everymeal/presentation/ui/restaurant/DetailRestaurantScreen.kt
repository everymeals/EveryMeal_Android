package com.everymeal.presentation.ui.restaurant

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.save.SaveTopBar
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray900
import kotlinx.coroutines.launch

@Composable
fun DetailRestaurantScreen(
    detailRestaurantViewModel: DetailRestaurantViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            SaveTopBar(title = "")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            item {
                DetailRestaurantImage()
            }
            item {
                DetailRestaurantInfo()
            }
            item {
                DetailRestaurantTabLayout(detailRestaurantViewModel)
            }
        }
    }
}

@Composable
fun DetailRestaurantImage(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(9f / 7f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.test_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(horizontal = 8.dp, vertical = 2.dp),
            text = "1/6",
            style = EveryMealTypography.bodySmall,
            fontSize = 14.sp,
            color = Color.White,
        )
    }
}

@Composable
fun DetailRestaurantInfo(

) {
    Column {
        Text(
            text = "정보",
            style = EveryMealTypography.labelSmall,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailRestaurantTabLayout(
    viewModel : DetailRestaurantViewModel
) {
    val viewState by viewModel.viewState.collectAsState()

    val pages = listOf("정보", "사진", "리뷰(0)")

    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.setEvent(DetailRestaurantEvent.OnTabSelectedChanged(page))
        }
    }

    TabRow(
        selectedTabIndex = viewState.selectedTabIndex,
        containerColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[viewState.selectedTabIndex]),
                color = Gray900,
            )
        },
        divider = { Divider(color = Gray500) },
    ) {
        pages.forEachIndexed { index, title ->
            Tab(
                text = { Text(
                    text = title,
                    style = EveryMealTypography.labelSmall,
                ) },
                selected = viewState.selectedTabIndex == index,
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
        when (page) {
            0 -> Text("This is Page 0", modifier = Modifier.fillMaxSize())
            1 -> Text("This is Page 1", modifier = Modifier.fillMaxSize())
            2 -> Text("This is Page 2", modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
fun PreviewDetailRestaurantScreen() {
    DetailRestaurantImage()
}
