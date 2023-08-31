package com.everymeal.presentation.ui.home

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class HomeContract {
    data class HomeState(
        val uiState: LoadState = LoadState.SUCCESS,
        val bottomSheetState: Boolean = false
    ) : ViewState

    sealed class HomeEvent : ViewEvent {
        data class BottomSheetStateChange(
            val bottomSheetState: Boolean
        ) : HomeEvent()
    }

    sealed class HomeEffect : ViewSideEffect {

    }
}