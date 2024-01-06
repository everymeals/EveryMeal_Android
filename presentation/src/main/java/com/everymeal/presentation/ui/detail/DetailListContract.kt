package com.everymeal.presentation.ui.detail

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class DetailContract {
    data class DetailState(
        val uiState: LoadState = LoadState.SUCCESS,
        val sortBottomSheetState: Boolean = false,
        val mealRatingBottomSheetState: Boolean = false,
        val reportBottomSheetState: Boolean = false,
        val detailReportBottomSheetState: Boolean = false,
        val detailSortCategoryType: DetailSortCategoryType = DetailSortCategoryType.POPULARITY,
        val reportCategoryType: ReportCategoryType = ReportCategoryType.NONE,
        val restaurantCategoryType: RestaurantCategoryType = RestaurantCategoryType.NONE,
        val rating: Int = 0,
    ) : ViewState

    sealed class DetailEvent : ViewEvent {
        object InitDetailScreen : DetailEvent()
        data class SortBottomSheetStateChange(val sortBottomSheetState: Boolean) : DetailEvent()
        data class MealRatingBottomSheetStateChange(val mealRatingBottomSheetState: Boolean) : DetailEvent()
        data class ReportBottomSheetStateChange(val reportBottomSheetState: Boolean) : DetailEvent()
        data class DetailReportBottomSheetStateChange(val detailReportBottomSheetState: Boolean) : DetailEvent()
        data class OnClickDetailListCategoryType(val detailSortCategoryType: DetailSortCategoryType) : DetailEvent()
        data class OnClickReportCategoryType(val reportCategoryType: ReportCategoryType) : DetailEvent()
        data class OnClickRestaurantCategoryType(val restaurantCategoryType: RestaurantCategoryType) : DetailEvent()
        data class OnClickRating(val rating: Int) : DetailEvent()
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

fun DetailSortCategoryType.sort(): String {
    return when (this) {
        DetailSortCategoryType.POPULARITY -> "grade"
        DetailSortCategoryType.DISTANCE -> "distance"
        DetailSortCategoryType.RECENT -> "registDate"
    }
}

enum class ReportCategoryType {
    IRRELAVANT,
    SLANG,
    LUSTFUL,
    NONE
}

fun String.ReportCategoryType(): ReportCategoryType {
    return when (this) {
        "해당 가게와 무관한 리뷰" -> ReportCategoryType.IRRELAVANT
        "비속어 및 혐오 발언" -> ReportCategoryType.SLANG
        "음란성 게시물" -> ReportCategoryType.LUSTFUL
        else -> ReportCategoryType.NONE
    }
}

fun ReportCategoryType.title(): String {
    return when (this) {
        ReportCategoryType.IRRELAVANT -> "해당 가게와 무관한 리뷰"
        ReportCategoryType.SLANG -> "비속어 및 혐오 발언"
        ReportCategoryType.LUSTFUL -> "음란성 게시물"
        ReportCategoryType.NONE -> ""
    }
}

enum class RestaurantCategoryType {
    RECOMMEND,
    RICE,
    CAFE,
    DRINK,
    NONE
}

fun String.RestaurantCategoryType(): RestaurantCategoryType {
    return when (this) {
        "추천" -> RestaurantCategoryType.RECOMMEND
        "밥집" -> RestaurantCategoryType.RICE
        "카페" -> RestaurantCategoryType.CAFE
        "술집" -> RestaurantCategoryType.DRINK
        else -> RestaurantCategoryType.RECOMMEND
    }
}

fun RestaurantCategoryType.title(): String {
    return when (this) {
        RestaurantCategoryType.RECOMMEND -> "추천"
        RestaurantCategoryType.RICE -> "밥집"
        RestaurantCategoryType.CAFE -> "카페"
        RestaurantCategoryType.DRINK -> "술집"
        RestaurantCategoryType.NONE -> ""
    }
}

fun RestaurantCategoryType.sort() : String {
    return when (this) {
        RestaurantCategoryType.RECOMMEND -> "recommend"
        RestaurantCategoryType.RICE -> "restaurant"
        RestaurantCategoryType.CAFE -> "cafe"
        RestaurantCategoryType.DRINK -> "bar"
        RestaurantCategoryType.NONE -> "all"
    }
}