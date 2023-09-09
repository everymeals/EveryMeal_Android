package com.everymeal.presentation.ui.review

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

data class ReviewState(
    var starRatingStateList: List<State<Boolean>> = listOf(
        mutableStateOf(true),
        mutableStateOf(true),
        mutableStateOf(true),
        mutableStateOf(true),
        mutableStateOf(true),
    )
) : ViewState

sealed class ReviewEvent : ViewEvent {
    data class OnStarClicked(
        val starIndex: Int
    ) : ReviewEvent()
}

sealed class ReviewEffect : ViewSideEffect {

}

