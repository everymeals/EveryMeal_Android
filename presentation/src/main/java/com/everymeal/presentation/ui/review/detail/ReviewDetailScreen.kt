package com.everymeal.presentation.ui.review.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.review.ReviewTopBar
import com.everymeal.presentation.ui.review.StarRating
import com.everymeal.presentation.ui.theme.Gray800

@Composable
fun ReviewDetailScreen() {
    val mockRatingList = remember {
        listOf(
            mutableStateOf(true),
            mutableStateOf(true),
            mutableStateOf(true),
            mutableStateOf(true),
            mutableStateOf(true)
        )
    }
    Scaffold(
        topBar = {
            ReviewTopBar(
                title = stringResource(R.string.review_title),
                onBackClicked = {

                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            UserProfileAppbar(
                userName = "햄식이",
                ratingList = mockRatingList
            )
            FoodImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(top = 24.dp)
            )
            ReviewText(
                modifier = Modifier.padding(
                    vertical = 20.dp,
                    horizontal = 20.dp,
                ),
            )
        }
    }
}

@Composable
private fun UserProfileAppbar(
    userName: String,
    userProfileUrl: String? = null,
    ratingList: List<MutableState<Boolean>>
) {
    Row {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.profile_ex_image),
            contentDescription = "profile"
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = userName,
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                color = Gray800,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                StarRating(
                    modifier = Modifier.padding(horizontal = 1.dp),
                    ratingStateList = ratingList,
                    starSize = 14.dp
                )
                // TODO 시간 계산 필요
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "3일전",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Gray800,
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .size(24.dp)
                .padding(end = 20.dp),
            painter = painterResource(id = R.drawable.icon_dots_mono),
            contentDescription = "more"
        )
    }
}

@Composable
private fun FoodImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.food_ex_2),
            contentDescription = "more"
        )
        LocationButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 16.dp,
                    bottom = 16.dp
                )
        )
        PageInfo(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
    }
}

@Composable
private fun LocationButton(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0x99000000),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 6.dp,
                vertical = 4.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(
                    id = R.drawable.icon_pin_location_mono
                ),
                contentDescription = "location"
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "성신 이자카야",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color.White,
            )
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.icon_arrow_right),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun PageInfo(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0x99000000),
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 2.dp
            ),
            text = "1/3",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color.White,
        )
    }
}

@Composable
fun ReviewText(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다. 이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다. 이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다. 이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다. 이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다. 이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다. 이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다. 이자카야는 늘 사람이 많아서 대기가 길지만, 맛있어서 자주 갑니다.",
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        color = Gray800,
    )
}

@Composable
@Preview
fun PreviewReviewDetailScreen() {
    ReviewDetailScreen()
}
