package com.everymeal.presentation.ui.review

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.everymeal.domain.model.review.UserReview
import com.everymeal.domain.usecase.review.PostReviewUseCase
import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewScreenViewModel @Inject constructor(
    private val postReviewUseCase: PostReviewUseCase
) : BaseViewModel<ReviewState, ReviewEffect, ReviewEvent>(ReviewState()) {
    override fun handleEvents(event: ReviewEvent) {
        when (event) {
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

            is ReviewEvent.PostReview -> {
                viewModelScope.launch {
                    val userReview = UserReview(
                        mealIdx = event.mealIdx,
                        grade = event.starRatingCount,
                        content = event.reviewValue,
                        imageList = event.imageUri.map { it.toString() }
                    )
                    postReviewUseCase(userReview)
                }

            }
        }
    }
}
