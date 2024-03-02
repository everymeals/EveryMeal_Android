package com.everymeal.presentation.ui.review

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.everymeal.domain.model.review.UserReview
import com.everymeal.domain.usecase.restaurant.GetDetailRestaurantUseCase
import com.everymeal.domain.usecase.review.PostReviewUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReviewState(
    val starRatingStateList: List<State<Boolean>> = listOf(
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
    ),
    val imageUri: List<Uri> = listOf(),
    val restaurantIdx: Int = 0,
    val restaurantType: String = "주점",
    val restaurantName: String = "성신 이자카야",
    val reviewValue: String = "",
) : ViewState

sealed class ReviewEvent : ViewEvent {
    data class SetRestaurantIdx(
        val idx: Int,
    ) : ReviewEvent()

    data class OnStarClicked(
        val starIndex: Int,
    ) : ReviewEvent()

    data class OnReviewTextChanged(
        val reviewValue: String,
    ) : ReviewEvent()

    data class OnImageSelected(
        val imageUri: List<Uri>,
    ) : ReviewEvent()

    object OnReviewSave : ReviewEvent()
    data class RemovePhotoUri(
        val imageUri: Uri,
    ) : ReviewEvent()
}

sealed class ReviewEffect : ViewSideEffect {
    object OnReviewSaveSuccess : ReviewEffect()

    object OnReviewSaveFail : ReviewEffect()
}


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val postReviewUseCase: PostReviewUseCase,
    private val getDetailRestaurantUseCase: GetDetailRestaurantUseCase
) : BaseViewModel<ReviewState, ReviewEffect, ReviewEvent>(ReviewState()) {
    override fun handleEvents(event: ReviewEvent) {
        when (event) {
            is ReviewEvent.SetRestaurantIdx -> {
                getRestaurantDetail(event.idx)
            }

            is ReviewEvent.OnStarClicked -> {
                updateState {
                    val newStarRatingStateList = List(starRatingStateList.size) { index ->
                        mutableStateOf(index <= event.starIndex)
                    }
                    copy(
                        starRatingStateList = newStarRatingStateList,
                    )
                }
            }

            is ReviewEvent.OnReviewTextChanged -> {
                updateState {
                    copy(
                        reviewValue = event.reviewValue,
                    )
                }
            }

            is ReviewEvent.OnImageSelected -> {
                updateState {
                    copy(
                        imageUri = event.imageUri,
                    )
                }
            }

            is ReviewEvent.OnReviewSave -> {
                reviewSave()
            }

            is ReviewEvent.RemovePhotoUri -> {
                updateState {
                    copy(
                        imageUri = imageUri.filter { it != event.imageUri }
                    )
                }
            }

        }
    }

    private fun getRestaurantDetail(restaurantIdx: Int) {
        viewModelScope.launch {
            getDetailRestaurantUseCase(restaurantIdx).onSuccess {
                updateState {
                    copy(
                        restaurantIdx = it.idx,
                        restaurantName = it.name,
                        restaurantType = it.categoryDetail,
                    )
                }
            }
        }
    }

    private fun reviewSave() {
        val viewState = viewState.value
        val grade = viewState.starRatingStateList.count { it.value }
        val imageList = viewState.imageUri.map { it.toString() }
        viewModelScope.launch {
            val userReview = UserReview(
                idx = viewState.restaurantIdx,
                grade = grade,
                content = viewState.reviewValue,
                imageList = imageList,
            )
            postReviewUseCase(userReview).onSuccess {
                sendEffect({ ReviewEffect.OnReviewSaveSuccess })
            }.onFailure {
                sendEffect({ ReviewEffect.OnReviewSaveFail })
            }
        }
    }
}
