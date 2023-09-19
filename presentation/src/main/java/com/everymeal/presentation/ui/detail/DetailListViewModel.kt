package com.everymeal.presentation.ui.detail

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.detail.DetailContract.DetailEvent
import com.everymeal.presentation.ui.detail.DetailContract.DetailState
import com.everymeal.presentation.ui.detail.DetailContract.HomeEffect
import javax.inject.Inject

class DetailListViewModel @Inject constructor(

): BaseViewModel<DetailState, HomeEffect, DetailEvent>(
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
                updateState {
                    copy(
                        sortBottomSheetState = event.sortBottomSheetState
                    )
                }
            }
            is DetailEvent.MealRatingBottomSheetStateChange -> {
                updateState {
                    copy(
                        mealRatingBottomSheetState = event.mealRatingBottomSheetState
                    )
                }
            }
        }
    }

    private fun reflectUpdateState(
        detailSortCategoryType: DetailSortCategoryType = viewState.value.detailSortCategoryType,
        sortBottomSheetState: Boolean = viewState.value.sortBottomSheetState,
        mealRatingBottomSheetState: Boolean = viewState.value.mealRatingBottomSheetState,
    ) {
        updateState {
            copy(
                detailSortCategoryType = detailSortCategoryType,
                sortBottomSheetState = sortBottomSheetState,
                mealRatingBottomSheetState = mealRatingBottomSheetState,
            )
        }
    }
}