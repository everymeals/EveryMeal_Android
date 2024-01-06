package com.everymeal.presentation.ui.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.domain.usecase.restaurant.GetUnivRestaurantUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.detail.DetailContract.DetailEvent
import com.everymeal.presentation.ui.detail.DetailContract.DetailState
import com.everymeal.presentation.ui.detail.DetailContract.DetailEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailListViewModel @Inject constructor(
    private val getUnivRestaurantUseCase: GetUnivRestaurantUseCase
): BaseViewModel<DetailState, DetailEffect, DetailEvent>(
    DetailState()
) {
    private val _restaurantItems : MutableStateFlow<PagingData<RestaurantDataEntity>> = MutableStateFlow(value = PagingData.empty())
    val restaurantItems : StateFlow<PagingData<RestaurantDataEntity>> = _restaurantItems.asStateFlow()

    override fun handleEvents(event: DetailEvent) {
        when (event) {
            is DetailEvent.InitDetailScreen -> {
                getRestaurantList()
            }
            is DetailEvent.OnClickDetailListCategoryType -> {
                reflectUpdateState(
                    detailSortCategoryType = event.detailSortCategoryType
                )
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
        }
    }

    private fun getRestaurantList() {
        viewModelScope.launch {
            getUnivRestaurantUseCase(1, "name", null, null)
                .cachedIn(viewModelScope)
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
            )
        }
    }
}