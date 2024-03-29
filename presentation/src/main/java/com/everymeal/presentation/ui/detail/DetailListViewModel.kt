package com.everymeal.presentation.ui.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.domain.model.review.StoreReviewEntity
import com.everymeal.domain.usecase.local.GetUniversityIndexUseCase
import com.everymeal.domain.usecase.restaurant.GetUnivRestaurantUseCase
import com.everymeal.domain.usecase.review.GetStoreReviewUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.detail.DetailContract.DetailEvent
import com.everymeal.presentation.ui.detail.DetailContract.DetailState
import com.everymeal.presentation.ui.detail.DetailContract.DetailEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailListViewModel @Inject constructor(
    private val getUnivRestaurantUseCase: GetUnivRestaurantUseCase,
    private val getUniversityIndexUseCase: GetUniversityIndexUseCase,
    private val getStoreReviewUseCase: GetStoreReviewUseCase
): BaseViewModel<DetailState, DetailEffect, DetailEvent>(
    DetailState()
) {
    private val _restaurantItems : MutableStateFlow<PagingData<RestaurantDataEntity>> = MutableStateFlow(value = PagingData.empty())
    val restaurantItems : StateFlow<PagingData<RestaurantDataEntity>> = _restaurantItems.asStateFlow()

    private val _restaurantReviews : MutableStateFlow<PagingData<StoreReviewEntity>> = MutableStateFlow(value = PagingData.empty())
    val restaurantReviews : StateFlow<PagingData<StoreReviewEntity>> = _restaurantReviews.asStateFlow()

    override fun handleEvents(event: DetailEvent) {
        when (event) {
            is DetailEvent.InitDetailScreen -> {
                reflectUpdateState(
                    isReviewScreen = event.isReviewScreen
                )
                fetchListBasedOnType(event.isReviewScreen)
            }
            is DetailEvent.OnClickDetailListCategoryType -> {
                reflectUpdateState(
                    detailSortCategoryType = event.detailSortCategoryType
                )
                fetchListBasedOnType(viewState.value.isReviewScreen)
            }
            is DetailEvent.SortBottomSheetStateChange -> {
                reflectUpdateState(
                    sortBottomSheetState = event.sortBottomSheetState
                )
            }
            is DetailEvent.MealRatingBottomSheetStateChange -> {
                reflectUpdateState(
                    mealRatingBottomSheetState = event.mealRatingBottomSheetState
                )
                fetchListBasedOnType(viewState.value.isReviewScreen)
            }
            is DetailEvent.ReportBottomSheetStateChange -> {
                reflectUpdateState(
                    reportBottomSheetState = event.reportBottomSheetState
                )
            }
            is DetailEvent.DetailReportBottomSheetStateChange -> {
                reflectUpdateState(
                    detailReportBottomSheetState = event.detailReportBottomSheetState
                )
            }
            is DetailEvent.OnClickReportCategoryType -> {
                reflectUpdateState(
                    reportCategoryType = event.reportCategoryType
                )
            }
            is DetailEvent.OnClickRating -> {
                reflectUpdateState(
                    rating = event.rating
                )
            }
            is DetailEvent.OnClickRestaurantCategoryType -> {
                reflectUpdateState(
                    restaurantCategoryType = event.restaurantCategoryType
                )
            }
            is DetailEvent.OnDeleteClickRestaurantCategoryType -> {
                reflectUpdateState(
                    restaurantCategoryType = RestaurantCategoryType.NONE
                )
                fetchListBasedOnType(viewState.value.isReviewScreen)
            }
            is DetailEvent.OnDeleteClickRating -> {
                reflectUpdateState(
                    rating = 0
                )
                fetchListBasedOnType(viewState.value.isReviewScreen)
            }
            is DetailEvent.OnRestaurantDetailClick -> {
                sendEffect({ DetailEffect.OnRestaurantClickEffect(event.restaurantId) })
            }
        }
    }

    private fun fetchListBasedOnType(isReviewScreen: Boolean) {
        if(isReviewScreen) {
            getReviewList()
        } else {
            getRestaurantList()
        }
    }

    private fun getReviewList() {
        viewModelScope.launch {
            getStoreReviewUseCase(
                order = viewState.value.detailSortCategoryType.sort(),
                group = viewState.value.restaurantCategoryType.sort(),
                grade = if(viewState.value.rating == 0) null else viewState.value.rating,
                campusIdx = getUniversityIndexUseCase().first().toInt()
            ).cachedIn(viewModelScope)
             .collect {
                _restaurantReviews.emit(it)
             }
        }
    }

    private fun getRestaurantList() {
        viewModelScope.launch {
            getUnivRestaurantUseCase(
                campusIdx = getUniversityIndexUseCase().first().toInt(),
                order = viewState.value.detailSortCategoryType.sort(),
                group = viewState.value.restaurantCategoryType.sort(),
                grade = if(viewState.value.rating == 0) null else viewState.value.rating.toString()
            ).cachedIn(viewModelScope)
             .collect {
                _restaurantItems.emit(it)
             }
        }
    }

    private fun reflectUpdateState(
        detailSortCategoryType: DetailSortCategoryType = viewState.value.detailSortCategoryType,
        sortBottomSheetState: Boolean = viewState.value.sortBottomSheetState,
        mealRatingBottomSheetState: Boolean = viewState.value.mealRatingBottomSheetState,
        reportBottomSheetState: Boolean = viewState.value.reportBottomSheetState,
        detailReportBottomSheetState: Boolean = viewState.value.detailReportBottomSheetState,
        reportCategoryType: ReportCategoryType = viewState.value.reportCategoryType,
        rating: Int = viewState.value.rating,
        restaurantCategoryType: RestaurantCategoryType = viewState.value.restaurantCategoryType,
        isReviewScreen: Boolean = viewState.value.isReviewScreen
    ) {
        updateState {
            copy(
                detailSortCategoryType = detailSortCategoryType,
                sortBottomSheetState = sortBottomSheetState,
                mealRatingBottomSheetState = mealRatingBottomSheetState,
                reportBottomSheetState = reportBottomSheetState,
                detailReportBottomSheetState = detailReportBottomSheetState,
                reportCategoryType = reportCategoryType,
                rating = rating,
                restaurantCategoryType = restaurantCategoryType,
                isReviewScreen = isReviewScreen
            )
        }
    }
}
