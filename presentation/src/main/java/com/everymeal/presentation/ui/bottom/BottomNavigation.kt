package com.everymeal.presentation.ui.bottom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.everymeal.presentation.R

enum class BottomNavigation(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    HOME(
        route = EveryMealRoute.HOME.route,
        icon = R.drawable.icon_store,
        title = R.string.bottom_nav_home,
    ),
    UNIV_FOOD(
        route = EveryMealRoute.UNIV_FOOD.route,
        icon = R.drawable.icon_folk,
        title = R.string.bottom_nav_univ_food,
    ),
    WHAT_FOOD(
        route = EveryMealRoute.WHAT_FOOD.route,
        icon = R.drawable.icon_chat_bubble,
        title = R.string.bottom_nav_what_food,
    ),
    MY_PAGE(
        route = EveryMealRoute.MY_PAGE.route,
        icon = R.drawable.icon_user,
        title = R.string.bottom_nav_my_tab,
    ),
}

enum class EveryMealRoute(val route: String) {
    HOME("home"),
    SEARCH("search"),
    UNIV_FOOD("univ-food"),
    WHAT_FOOD("what-food"),
    MY_PAGE("my-page"),
    DETAIL_LIST("detail-list"),
    DETAIL_RESTAURANT("detail-restaurant"),
    SCHOOL_AUTH("school-auth"),
    REVIEW_SEARCH("review-search"),
}
