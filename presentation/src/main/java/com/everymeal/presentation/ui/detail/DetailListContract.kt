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

fun String.DetailSortCategoryType(): DetailSortCategoryType {
    return when (this) {
        "인기순" -> DetailSortCategoryType.POPULARITY
        "거리순" -> DetailSortCategoryType.DISTANCE
        "최신순" -> DetailSortCategoryType.RECENT
        else -> DetailSortCategoryType.POPULARITY
    }
}

fun DetailSortCategoryType.title(): String {
    return when (this) {
        DetailSortCategoryType.POPULARITY -> "인기순"
        DetailSortCategoryType.DISTANCE -> "거리순"
        DetailSortCategoryType.RECENT -> "최신순"
    }
}