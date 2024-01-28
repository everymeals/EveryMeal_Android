package com.everymeal.presentation.ui.restaurant

import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState
import com.everymeal.presentation.ui.signup.UnivSelectContract


data class DetailRestaurantState(
    val uiState: LoadState = LoadState.SUCCESS,
    val selectedTabIndex: Int = 0,
    val isFabClicked: Boolean = false,
    val getDetailRestaurantState: LoadState = LoadState.LOADING,
    val restaurantInfo: RestaurantDataEntity = RestaurantDataEntity(
        idx = 0,
        name = "",
        address = "",
        phoneNumber = "",
        categoryDetail = "",
        distance = 0,
        grade = 0f,
        reviewCount = 0,
        recommendedCount = 0,
        images = null,
        isLiked = false
    ),
    val networkErrorDialog: Boolean = true,
) : ViewState

sealed class DetailRestaurantEvent : ViewEvent {
    data class OnTabSelectedChanged(val selectedTabIndex: Int) : DetailRestaurantEvent()
    data class OnFloatingButtonClick(val isFabClicked: Boolean) : DetailRestaurantEvent()
    data class InitDetailRestaurantScreen(val restaurantId: Int) : DetailRestaurantEvent()
    data class NetworkErrorDialogClicked(val dialogStateChange: Boolean) : DetailRestaurantEvent()
}

sealed class DetailRestaurantEffect : ViewSideEffect {

}