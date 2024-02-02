package com.everymeal.presentation.ui.review

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

data class ReviewState(
    val starRatingStateList: List<State<Boolean>> = listOf(
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
    ),
    val imageUri: List<Uri> = listOf(),
    val idx: Int = 0,
    val restaurantType: String = "주점",
    val restaurantName: String = "성신 이자카야",
    val reviewValue: String = "",
) : ViewState

sealed class ReviewEvent : ViewEvent {
    data class OnStarClicked(
        val starIndex: Int,
    ) : ReviewEvent()

    data class OnReviewTextChanged(
        val reviewValue: String,
    ) : ReviewEvent()

    data class OnImageSelected(
        val imageUri: List<Uri>,
    ) : ReviewEvent()

    data class PostReview(
        val mealIdx: Int,
        val reviewValue: String,
        val imageUri: List<Uri>,
        val restaurantType: String,
        val restaurantName: String,
        val starRatingCount: Int,
    ) : ReviewEvent()
}

sealed class ReviewEffect : ViewSideEffect
