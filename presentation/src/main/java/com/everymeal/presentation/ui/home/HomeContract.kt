package com.everymeal.presentation.ui.home

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class HomeContract {
    data class HomeState(
        val uiState: LoadState = LoadState.SUCCESS,
    ) : ViewState

    sealed class HomeEvent : ViewEvent {

    }

    sealed class HomeEffect : ViewSideEffect {

    }
}