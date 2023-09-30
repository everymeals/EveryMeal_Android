package com.everymeal.presentation.ui.detail

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class DetailContract {
    data class DetailState(
        val uiState: LoadState = LoadState.SUCCESS,
        val detailSortCategoryType: DetailSortCategoryType = DetailSortCategoryType.POPULARITY,
        val sortBottomSheetState: Boolean = false,
        val mealRatingBottomSheetState: Boolean = false,
        val reportBottomSheetState: Boolean = false,
        val detailReportBottomSheetState: Boolean = false,
        val reportCategoryType: ReportCategoryType = ReportCategoryType.IRRELAVANT
    ) : ViewState

    sealed class DetailEvent : ViewEvent {
        data class OnClickDetailListCategoryType(val detailSortCategoryType: DetailSortCategoryType) : DetailEvent()
        data class SortBottomSheetStateChange(val sortBottomSheetState: Boolean) : DetailEvent()
        data class MealRatingBottomSheetStateChange(val mealRatingBottomSheetState: Boolean) : DetailEvent()
        data class ReportBottomSheetStateChange(val reportBottomSheetState: Boolean) : DetailEvent()
        data class DetailReportBottomSheetStateChange(val detailReportBottomSheetState: Boolean) : DetailEvent()
        data class OnClickReportCategoryType(val reportCategoryType: ReportCategoryType) : DetailEvent()
    }

    sealed class DetailEffect : ViewSideEffect {

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

enum class ReportCategoryType {
    IRRELAVANT,
    SLANG,
    LUSTFUL
}

fun String.ReportCategoryType(): ReportCategoryType {
    return when (this) {
        "해당 가게와 무관한 리뷰" -> ReportCategoryType.IRRELAVANT
        "비속어 및 혐오 발언" -> ReportCategoryType.SLANG
        "음란성 게시물" -> ReportCategoryType.LUSTFUL
        else -> ReportCategoryType.IRRELAVANT
    }
}

fun ReportCategoryType.title(): String {
    return when (this) {
        ReportCategoryType.IRRELAVANT -> "해당 가게와 무관한 리뷰"
        ReportCategoryType.SLANG -> "비속어 및 혐오 발언"
        ReportCategoryType.LUSTFUL -> "음란성 게시물"
    }
}