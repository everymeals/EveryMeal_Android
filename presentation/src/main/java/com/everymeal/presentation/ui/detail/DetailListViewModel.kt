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
                updateState {
                    copy(
                        detailSortCategoryType = event.detailSortCategoryType
                    )
                }
            }
            is DetailEvent.BottomSheetStateChange -> {
                updateState {
                    copy(
                        sortBottomSheetState = event.sortBottomSheetState
                    )
                }
            }
        }
    }
}