package com.everymeal.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.everymeal.presentation.ui.home.Review

@Composable
fun EveryMealReviewItem(
    review: Review,
    onDetailRestaurantClick: () -> Unit,
) {

}

@Preview
@Composable
fun EveryMealReviewItemPreview() {
    EveryMealReviewItem(
        review = Review(
            name = "슈니",
            profileImage = 0,
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