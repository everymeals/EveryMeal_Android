package com.everymeal.presentation.ui.review.write

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.components.EveryMealTextField
import com.everymeal.presentation.ui.review.RestaurantName
import com.everymeal.presentation.ui.review.RestaurantType
import com.everymeal.presentation.ui.review.ReviewState
import com.everymeal.presentation.ui.review.StarRating

@Composable
fun ReviewWriteScreen(
    viewState: ReviewState,
    starRatingClicked: (Int) -> Unit,
    reviewTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        RestaurantType(viewState = viewState)
        RestaurantName(viewState = viewState)
        StarRating(
            ratingStateList = viewState.starRatingStateList,
            starRatingClicked = starRatingClicked,
            starSize = 20.dp
        )
        EveryMealTextField(
            value = viewState.reviewValue,
            onValueChange = reviewTextChanged,
            placeholderText = "맛집에 대한 의견을 자세히 적어주시면 다른 사용자에게 도움이 돼요!\n\n",
        )
    }
}
