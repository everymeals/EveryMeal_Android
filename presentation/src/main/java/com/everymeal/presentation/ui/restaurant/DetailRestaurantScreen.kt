package com.everymeal.presentation.ui.restaurant

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.save.SaveTopBar
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray400
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray700
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Gray900
import com.everymeal.presentation.ui.theme.Main100
import kotlinx.coroutines.launch

@Composable
fun DetailRestaurantScreen(
    detailRestaurantViewModel: DetailRestaurantViewModel = hiltViewModel(),
) {
    val viewState by detailRestaurantViewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            SaveTopBar(title = "")
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(if(viewState.isFabClicked) Gray100 else Main100)
                    .padding(12.dp)
                    .clickable {
                        detailRestaurantViewModel.setEvent(
                            DetailRestaurantEvent.OnFloatingButtonClick(
                                !viewState.isFabClicked
                            )
                        )
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    imageVector =
                    if(viewState.isFabClicked)
                        ImageVector.vectorResource(R.drawable.icon_x_mono)
                    else
                        ImageVector.vectorResource(R.drawable.icon_pencil_mono),
                    contentDescription = "floating",
                    colorFilter = ColorFilter.tint(if(viewState.isFabClicked) Gray800 else Color.White),
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            item {
                DetailRestaurantImage()
            }
            item {
                DetailRestaurantMainInfo()
            }
            item {
                DetailRestaurantTabLayout(detailRestaurantViewModel)
            }
        }
        if (viewState.isFabClicked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable {
                        detailRestaurantViewModel.setEvent(
                            DetailRestaurantEvent.OnFloatingButtonClick(
                                false
                            )
                        )
                    },
                contentAlignment=Alignment.BottomEnd
            ) {
                Button(onClick={/*Handle click*/},modifier=Modifier.padding(end=20.dp, bottom=160.dp)){ Text("Button 1") }
                Button(onClick={/*Handle click*/},modifier=Modifier.padding(end=20.dp, bottom=100.dp)){ Text("Button 2") }
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
fun DetailRestaurantMainInfo(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(color = Color.White)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = Gray300)
                .padding(vertical = 3.dp, horizontal = 6.dp),
            text = "술집",
            color = Gray600,
            fontSize = 12.sp,
            style = EveryMealTypography.labelSmall,
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = "스타벅스 서울대 입구점",
            color = Color.Black,
            fontSize = 22.sp,
            style = EveryMealTypography.titleMedium,
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.icon_active_star_mono),
                contentDescription = stringResource(R.string.icon_star),
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "5.0",
                color = Gray700,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "(100)",
                color = Gray700,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Gray300,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .weight(1f)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.icon_heart_mono),
                    contentDescription = stringResource(R.string.icon_star),
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "89",
                    color = Gray500,
                    fontSize = 15.sp,
                    style = EveryMealTypography.displaySmall
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .background(
                        color = Gray100,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_download_mono),
                    contentDescription = "restaurant share",
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = stringResource(R.string.restaurant_share),
                    color = Gray600,
                    fontSize = 15.sp,
                    style = EveryMealTypography.displaySmall
                )
            }
        }
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
            0 -> DetailRestaurantTabInfo(Modifier.padding(horizontal = 20.dp))
            1 -> DetailRestaurantTabImage()
            2 -> DetailRestaurantReview()
        }
    }
}

@Composable
fun DetailRestaurantTabInfo(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                imageVector = ImageVector.vectorResource(R.drawable.icon_pin_location_mono),
                contentDescription = "location",
                colorFilter = ColorFilter.tint(Gray400),
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "서울특별시 관악구 관악로 1",
                color = Gray800,
                fontSize = 14.sp,
                style = EveryMealTypography.bodySmall
            )
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.icon_call_mono),
                contentDescription = "call",
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "1533-2233",
                color = Gray800,
                fontSize = 14.sp,
                style = EveryMealTypography.bodySmall
            )
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.icon_link_mono),
                contentDescription = "link",
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "카카오맵 이동하기",
                color = Gray800,
                fontSize = 14.sp,
                style = EveryMealTypography.bodySmall
            )
        }
        Spacer(modifier = Modifier.padding(18.dp))
    }
}

@Composable
fun DetailRestaurantTabImage() {
    // Mock Data
    val items = listOf(
        R.drawable.food_ex_1,
        R.drawable.food_ex_2,
        R.drawable.food_ex_3,
        R.drawable.food_ex_1,
        R.drawable.food_ex_2,
        R.drawable.food_ex_3,
        R.drawable.food_ex_1,
        R.drawable.food_ex_2,
        R.drawable.food_ex_3,
    )

    Column {
        for (rowItems in items.chunked(3)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 3.dp),
            ) {
                for (item in rowItems) {
                    Image(
                        painter = painterResource(id = item),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = if (rowItems.indexOf(item) != 2) 3.dp else 0.dp)
                            .weight(1f)
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailRestaurantReview() {

}

@Preview
@Composable
fun PreviewDetailRestaurantScreen() {
    DetailRestaurantScreen()
}

@Preview
@Composable
fun PreviewDetailRestaurantImage() {
    DetailRestaurantImage()
}
