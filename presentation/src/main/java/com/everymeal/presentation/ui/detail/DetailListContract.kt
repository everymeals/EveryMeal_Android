package com.everymeal.presentation.ui.detail

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class DetailContract {
    data class DetailState(
        val uiState: LoadState = LoadState.SUCCESS,
        val detailSortCategoryType: DetailSortCategoryType = DetailSortCategoryType.POPULARITY,
        val bottomSheetState: Boolean = false
    ) : ViewState

    sealed class DetailEvent : ViewEvent {
        data class OnClickDetailListCategoryType(val detailSortCategoryType: DetailSortCategoryType) : DetailEvent()
        data class BottomSheetStateChange(val bottomSheetState: Boolean) : DetailEvent()
    }

    sealed class HomeEffect : ViewSideEffect {

    }
}

enum class DetailSortCategoryType {
    POPULARITY,
    DISTANCE,
    RECENT
}