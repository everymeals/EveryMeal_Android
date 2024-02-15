package com.everymeal.presentation.ui.search

import androidx.paging.PagingData
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
대학교 불러오기 LoadState
대학교 선택하기 State Hoisting
 */
data class SearchState(
    val searchQuery: String = "",
    val searchIsShowHistory: Boolean = true,
    val searchHistoryItems: List<String> = listOf(),
    val searchResultList: Flow<PagingData<RestaurantDataEntity>> = flow {  },
) : ViewState

sealed class SearchEvent : ViewEvent {
    data class SearchQueryChanged(
        val query: String,
    ) : SearchEvent()

    data class UpdateSearchHistory(
        val historyItems: List<String>,
    ) : SearchEvent()

    object SearchRestaurant : SearchEvent()
}

sealed class SearchEffect : ViewSideEffect
