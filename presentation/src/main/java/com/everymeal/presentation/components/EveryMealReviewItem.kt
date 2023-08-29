package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.home.Review
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray800

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
        Spacer(modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun ReviewTitle(review: Review) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = review.profileImage),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(end = 8.dp))
        Column {
            Text(
                text = review.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Gray800,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = review.reviewDate,
                fontSize = 12.sp,
                color = Gray500,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
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
            rating = 5,
            reviewDate = "20210901",
            content = "맛있어요",
            restaurantName = "왕가주방",
        ),
        onDetailRestaurantClick = { },
    )
}