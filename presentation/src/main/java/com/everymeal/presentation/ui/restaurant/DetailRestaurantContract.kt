package com.everymeal.presentation.ui.restaurant

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState


data class DetailRestaurantState(
    val uiState: LoadState = LoadState.SUCCESS,
    val selectedTabIndex: Int = 0,
) : ViewState

sealed class DetailRestaurantEvent : ViewEvent {
    data class OnTabSelectedChanged(val selectedTabIndex: Int) : DetailRestaurantEvent()
}

sealed class DetailRestaurantEffect : ViewSideEffect {

}