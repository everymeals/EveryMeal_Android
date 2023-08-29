package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.home.Review
import com.everymeal.presentation.ui.theme.Gray600
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
        Spacer(modifier = Modifier.padding(12.dp))
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

@Preview
@Composable
fun EveryMealReviewItemPreview() {
    EveryMealReviewItem(
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
            content = "맛있어요",
            restaurantName = "왕가주방",
        ),
        onDetailRestaurantClick = { },
    )
}