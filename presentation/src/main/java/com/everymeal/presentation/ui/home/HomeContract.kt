package com.everymeal.presentation.ui.home

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class HomeContract {
    data class HomeState(
        val uiState: LoadState = LoadState.SUCCESS,
        val detailListScreenType: DetailListScreenType = DetailListScreenType.RECOMMEND
    ) : ViewState

    sealed class HomeEvent : ViewEvent {
        data class OnClickDetailList(val detailListScreenType: DetailListScreenType) : HomeEvent()
    }

    sealed class HomeEffect : ViewSideEffect {
        data class NavigateToDetailListScreen(val detailListScreenType: DetailListScreenType) : HomeEffect()
    }
}

enum class DetailListScreenType {
    RECOMMEND,
    RESTAURANT,
    CAFE,
    DRINK
}