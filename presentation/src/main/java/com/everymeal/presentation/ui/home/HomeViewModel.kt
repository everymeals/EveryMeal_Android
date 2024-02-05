package com.everymeal.presentation.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.everymeal.domain.usecase.local.GetUniversityIndexUseCase
import com.everymeal.domain.usecase.restaurant.GetHomeRestaurantUseCase
import com.everymeal.domain.usecase.restaurant.GetUnivRestaurantUseCase
import com.everymeal.domain.usecase.review.GetHomeReviewUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.ui.home.HomeContract.HomeEffect
import com.everymeal.presentation.ui.home.HomeContract.HomeEvent
import com.everymeal.presentation.ui.home.HomeContract.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Review(
    val name: String,
    val profileImage: Int,
    val loveCount: Int,
    val image: List<Int>,
    val rating: Int,
    val reviewDate: String,
    val content: String,
    val restaurantName: String,
)

data class Restaurant(
    val name: String,
    val category: String,
    val image: List<Int>,
    val rating: Double,
    val reviewCount: Int,
    val loveCount: Int,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeRestaurantUseCase: GetHomeRestaurantUseCase,
    private val getUniversityIndexUseCase: GetUniversityIndexUseCase,
    private val getHomeReviewUseCase: GetHomeReviewUseCase
): BaseViewModel<HomeState, HomeEffect, HomeEvent>(
    HomeState()
) {

    override fun handleEvents(event: HomeEvent) {
        when (event) {
            is HomeEvent.InitHomeScreen -> {
                getUnivRestaurant()
                getHomeReview()
            }
            is HomeEvent.OnClickDetailList -> {
                sendEffect({ HomeEffect.NavigateToDetailListScreen(event.detailListScreenType) })
            }

            is HomeEvent.BottomSheetStateChange -> {
                updateState {
                    copy(
                        bottomSheetState = event.bottomSheetState,
                    )
                }
            }
            is HomeEvent.OnClickDetailRestaurant -> {
                sendEffect({ HomeEffect.NavigateToDetailRestaurant(event.restaurantId) })
            }
        }
    }

    private fun getUnivRestaurant() {
        viewModelScope.launch {
            getHomeRestaurantUseCase(
                campusIdx = getUniversityIndexUseCase().first().toInt(),
                order = "name",
                group = null,
                grade = "1"
            ).onSuccess {
                updateState {
                    copy(
                        uiState = LoadState.SUCCESS,
                        restaurantData = it.data
                    )
                }
            }.onFailure {
                updateState {
                    copy(
                        uiState = LoadState.ERROR
                    )
                }
            }
        }
    }

    private fun getHomeReview() {
        viewModelScope.launch {
            getHomeReviewUseCase(
                offset = 0,
                limit = 3,
                order = "name",
                group = null,
                grade = null
            ).onSuccess {
                updateState {
                    copy(
                        reviewData = it.data
                    )
                }
            }.onFailure {
                Log.d("gg1234", "gg")
            }
        }
    }
}
