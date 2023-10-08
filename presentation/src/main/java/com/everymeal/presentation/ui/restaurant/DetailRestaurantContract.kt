package com.everymeal.presentation.ui.restaurant

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState


data class DetailRestaurantState(
    val uiState: LoadState = LoadState.SUCCESS,
    val selectedTabIndex: Int = 0,
    val isFabClicked: Boolean = false,
) : ViewState

sealed class DetailRestaurantEvent : ViewEvent {
    data class OnTabSelectedChanged(val selectedTabIndex: Int) : DetailRestaurantEvent()
    data class OnFloatingButtonClick(val isFabClicked: Boolean) : DetailRestaurantEvent()
}

sealed class DetailRestaurantEffect : ViewSideEffect {

}