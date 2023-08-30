package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.home.Review
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray700
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.util.Utils

@Composable
fun EveryMealReviewItem(
    review: Review,
    onDetailRestaurantClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(color = Color.White)
    ) {
        ReviewTitle(review)
        Spacer(modifier = Modifier.padding(10.dp))
        ReviewContent(review)
        Spacer(modifier = Modifier.padding(4.dp))
        ReviewGoodCount(review)
        Spacer(modifier = Modifier.padding(6.dp))
        ReviewDetailRestaurant(review, onDetailRestaurantClick)
    }
}

@Composable
fun ReviewTitle(review: Review) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .align(alignment = Alignment.CenterVertically),
            painter = painterResource(id = review.profileImage),
            contentDescription = stringResource(id = R.string.home_review_profile_image_description)
        )
        Spacer(modifier = Modifier.padding(end = 8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                text = review.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Gray800,
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(review.rating) {
                    Image(
                        modifier = Modifier
                            .size(12.dp)
                            .padding(end = 2.dp),
                        painter = painterResource(id = R.drawable.icon_star_mono),
                        contentDescription = stringResource(id = R.string.home_review_profile_review_score_description)
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(end = 4.dp))
                    Text(
                        text = Utils.getTimeAgo(review.reviewDate),
                        fontSize = 12.sp,
                        color = Gray600,
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .size(20.dp)
                .padding(top = 4.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_dots_mono),
            contentDescription = stringResource(id = R.string.home_review_profile_more_option_description)
        )
    }
}

@Composable
fun ReviewContent(review: Review) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = review.content,
            fontSize = 14.sp,
            color = Gray800,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.padding(end = 10.dp))
        Image(
            modifier = Modifier
                .size(64.dp)
                .align(alignment = Alignment.CenterVertically),
            painter = painterResource(id = review.image[0]),
            contentDescription = stringResource(id = R.string.home_review_image)
        )
    }
}

@Composable
fun ReviewGoodCount(review: Review) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(16.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_thumb_up_mono),
            contentDescription = stringResource(id = R.string.home_review_good_count)
        )
        Spacer(modifier = Modifier.padding(end = 2.dp))
        Text(
            text = review.loveCount.toString(),
            fontSize = 12.sp,
            color = Gray600,
        )
    }
}

@Composable
fun ReviewDetailRestaurant(
    review: Review,
    onDetailRestaurantClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable { onDetailRestaurantClick() }
            .background(Gray300),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 10.dp)
                .size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_pin_location_mono),
            contentDescription = stringResource(id = R.string.home_review_detail_restaurant)
        )
        Text(
            modifier = Modifier
                .weight(1f),
            text = review.restaurantName,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray700,
        )
        Image(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(16.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_right),
            contentDescription = stringResource(id = R.string.home_review_detail_restaurant)
        )
    }
}

@Preview
@Composable
fun ReviewDetailRestaurantPreview() {
    ReviewDetailRestaurant(
        review = Review(
            name = "슈니",
            profileImage = R.drawable.profile_ex_image,
            loveCount = 100,
            image = listOf(
                0,
                1,
            ),
            rating = 3,
            reviewDate = "2023-08-29T09:58:47.604732",
            content = "매장 안쪽으로 가면 너무 감성있는 곳이 나와요. 그리고 분위기도 너무 좋고 맛도 너무 완벽해요. 이런 카페는 정말 처음인 것 같아요. 알바생도 너무 아름답습니다.. 여기 계속 찾을 것 같아요. 정말 항상 감사드려요.",
            restaurantName = "왕가주방",
        ),
        onDetailRestaurantClick = { },
    )
}