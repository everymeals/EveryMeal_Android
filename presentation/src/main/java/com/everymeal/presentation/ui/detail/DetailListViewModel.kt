package com.everymeal.presentation.ui.detail

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.detail.DetailContract.DetailEvent
import com.everymeal.presentation.ui.detail.DetailContract.DetailState
import com.everymeal.presentation.ui.detail.DetailContract.DetailEffect
import javax.inject.Inject

class DetailListViewModel @Inject constructor(

): BaseViewModel<DetailState, DetailEffect, DetailEvent>(
    DetailState()
) {

    override fun handleEvents(event: DetailEvent) {
        when (event) {
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