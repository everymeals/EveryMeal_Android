package com.everymeal.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.everymeal.domain.usecase.local.GetUniversityIndexUseCase
import com.everymeal.domain.usecase.restaurant.GetHomeRestaurantUseCase
import com.everymeal.domain.usecase.restaurant.GetUnivRestaurantUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.ui.home.HomeContract.HomeEffect
import com.everymeal.presentation.ui.home.HomeContract.HomeState
import com.everymeal.presentation.ui.home.HomeContract.HomeEvent
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
    val restaurantName: String
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
    private val getUniversityIndexUseCase: GetUniversityIndexUseCase
): BaseViewModel<HomeState, HomeEffect, HomeEvent>(
    HomeState()
) {

    override fun handleEvents(event: HomeEvent) {
        when (event) {
            is HomeEvent.InitHomeScreen -> {
                getUnivRestaurant()
            }
            is HomeEvent.OnClickDetailList -> {
                sendEffect({ HomeEffect.NavigateToDetailListScreen(event.detailListScreenType) })
            }
            is HomeEvent.BottomSheetStateChange -> {
                updateState {
                    copy(
                        bottomSheetState = event.bottomSheetState
                    )
                }
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
}