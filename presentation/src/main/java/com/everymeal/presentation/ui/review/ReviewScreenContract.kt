package com.everymeal.presentation.ui.review

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

data class ReviewState(
    var starRatingStateList: List<State<Boolean>> = listOf(
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
    ),
    var restaurantType: String = "주점",
    var restaurantName: String = "성신 이자카야",
    var reviewValue: String = ""
) : ViewState

sealed class ReviewEvent : ViewEvent {
    data class OnStarClicked(
        val starIndex: Int
    ) : ReviewEvent()

    data class OnReviewTextChanged(
        val reviewValue: String
    ) : ReviewEvent()
}

sealed class ReviewEffect : ViewSideEffect {

}

