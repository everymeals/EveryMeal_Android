package com.everymeal.presentation.ui.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.review.write.ReviewWriteScreen
import com.everymeal.presentation.ui.search.topbar.SearchBar
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Grey2
import com.everymeal.presentation.ui.theme.Grey9
import com.everymeal.presentation.ui.theme.Typography

@Composable
fun ReviewScreen(
    viewModel: ReviewScreenViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    Scaffold(
        topBar = {
            ReviewTopBar()
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {
//                ReviewGuideHeader()
//                ReviewSearchBar(
//                    modifier = Modifier
//                        .padding(top = 28.dp)
//                        .padding(horizontal = 20.dp),
//                    searchBarClicked = {
//                        //TODO 화면 이동
//                    }
//                )
//                StarDetail(
//                    viewState = viewState,
//                    startRatingClicked = { index ->
//                        viewModel.setEvent(ReviewEvent.OnStarClicked(index))
//                    },
//                )
                ReviewWriteScreen(
                    viewState = viewState,
                    starRatingClicked = { index ->
                        viewModel.setEvent(ReviewEvent.OnStarClicked(index))
                    },
                    reviewTextChanged = {
                        viewModel.setEvent(ReviewEvent.OnReviewTextChanged(it))
                    },
                    onReviewRegisterClicked = {
                        // TODO 리뷰 등록
                    }
                )
            }
        }
    }
}

@Composable
fun ColumnScope.StarDetail(
    modifier: Modifier = Modifier,
    viewState: ReviewState,
    startRatingClicked: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 133.dp)
    ) {
        RestaurantType(
            viewState = viewState,
        )
        RestaurantName(
            viewState = viewState,
        )
        StarRating(
            modifier = Modifier
                .padding(top = 50.dp),
            ratingStateList = viewState.starRatingStateList,
            starRatingClicked = startRatingClicked
        )
    }
}

@Composable
fun ColumnScope.RestaurantType(
    modifier: Modifier = Modifier,
    viewState: ReviewState,
) {
    Text(
        modifier = modifier
            .align(Alignment.CenterHorizontally)
            .background(
                color = Grey2,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 6.dp, vertical = 3.dp),
        text = viewState.restaurantType,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        fontWeight = FontWeight(500),
        color = Gray600,
    )
}

@Composable
fun ColumnScope.RestaurantName(
    modifier: Modifier = Modifier,
    viewState: ReviewState
) {
    Text(
        modifier = modifier,
        text = viewState.restaurantName,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
        fontWeight = FontWeight(700),
        color = Grey9,
    )
}

@Composable
fun ReviewSearchBar(
    modifier: Modifier = Modifier,
    searchBarClicked: () -> Unit
) {
    SearchBar(
        modifier = modifier.clickable {
            searchBarClicked()
        },
        searchQuery = "",
        changeQuery = {},
        setShowHistory = {}
    )
}

@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    ratingStateList: List<State<Boolean>>,
    starRatingClicked: (Int) -> Unit,
    starSize: Dp = 40.dp,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(ratingStateList) { index, active ->
            Image(
                modifier = Modifier
                    .size(starSize)
                    .padding(horizontal = 1.dp)
                    .clickable {
                        starRatingClicked(index)
                    },
                painter = if (active.value) {
                    painterResource(
                        id = R.drawable.icon_active_star_mono
                    )
                } else {
                    painterResource(
                        id = R.drawable.icon_unactive_star_mono
                    )
                },
                contentDescription = null
            )
        }
    }
}

@Composable
fun ReviewGuideHeader(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .padding(start = 24.dp, top = 48.dp),
        text = "다녀온 맛집은\n어디인가요?",
        style = Typography.titleLarge,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "리뷰 작성",
                style = Typography.bodySmall,
                color = Grey9
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
                    .padding(end = 4.dp),
                painter = painterResource(id = R.drawable.icon_x_mono),
                contentDescription = null
            )
        }
    )
}

@Composable
@Preview
fun ReviewScreenPreview() {
    ReviewScreen()
}
