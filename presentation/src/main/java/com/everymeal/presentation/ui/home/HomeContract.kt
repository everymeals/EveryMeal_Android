package com.everymeal.presentation.ui.home

import com.everymeal.domain.model.restaurant.GetUnivRestaurantEntity
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class HomeContract {
    data class HomeState(
        val uiState: LoadState = LoadState.LOADING,
        val detailListScreenType: DetailListScreenType = DetailListScreenType.RECOMMEND,
        val bottomSheetState: Boolean = false,
        val restaurantData: List<RestaurantDataEntity>? = null
    ) : ViewState

    sealed class HomeEvent : ViewEvent {
        object InitHomeScreen : HomeEvent()
        data class OnClickDetailList(val detailListScreenType: DetailListScreenType) : HomeEvent()
        data class BottomSheetStateChange(val bottomSheetState: Boolean) : HomeEvent()
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

fun String.DetailListScreenType(): DetailListScreenType {
    return when (this) {
        "추천" -> DetailListScreenType.RECOMMEND
        "밥집" -> DetailListScreenType.RESTAURANT
        "카페" -> DetailListScreenType.CAFE
        "술집" -> DetailListScreenType.DRINK
        else -> DetailListScreenType.RECOMMEND
    }
}

fun DetailListScreenType.title(): String {
    return when (this) {
        DetailListScreenType.RECOMMEND -> "추천"
        DetailListScreenType.RESTAURANT -> "밥집"
        DetailListScreenType.CAFE -> "카페"
        DetailListScreenType.DRINK -> "술집"
    }
}