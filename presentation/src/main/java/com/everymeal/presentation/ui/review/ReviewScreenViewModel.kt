package com.everymeal.presentation.ui.review

import androidx.compose.runtime.mutableStateOf
import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewScreenViewModel @Inject constructor(

) : BaseViewModel<ReviewState, ReviewEffect, ReviewEvent>(ReviewState()) {
    override fun handleEvents(event: ReviewEvent) {
        when(event) {
            is ReviewEvent.OnStarClicked -> {
                updateState {
                    val newStarRatingStateList = List(starRatingStateList.size) { index ->
                        mutableStateOf(index <= event.starIndex)
                    }
                    copy(
                        starRatingStateList = newStarRatingStateList
                    )
                }
            }

            is ReviewEvent.OnReviewTextChanged -> {
                updateState {
                    copy(
                        reviewValue = event.reviewValue
                    )
                }
            }

            is ReviewEvent.OnImageSelected -> {
                updateState {
                    copy(
                        imageUri = event.imageUri
                    )
                }
            }
        }
    }
}
