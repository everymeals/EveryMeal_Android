package com.everymeal.presentation.ui.main

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.everymeal.domain.NetworkPreference
import com.everymeal.presentation.ui.bottom.EveryMealBottomNavigation
import com.everymeal.presentation.ui.bottom.EveryMealRoute
import com.everymeal.presentation.ui.bottom.navigateBottomNavigationScreen
import com.everymeal.presentation.ui.detail.DetailListScreen
import com.everymeal.presentation.ui.home.HomeScreen
import com.everymeal.presentation.ui.mypage.MyPageScreen
import com.everymeal.presentation.ui.profile.ProfileGenerateScreen
import com.everymeal.presentation.ui.restaurant.DetailRestaurantScreen
import com.everymeal.presentation.ui.review.ReviewScreen
import com.everymeal.presentation.ui.review.ReviewViewModel
import com.everymeal.presentation.ui.review.search.ReviewSearchScreen
import com.everymeal.presentation.ui.review.write.ReviewWriteScreen
import com.everymeal.presentation.ui.search.SearchScreen
import com.everymeal.presentation.ui.signup.school.SchoolAuthScreen
import com.everymeal.presentation.ui.univfood.UnivFoodScreen
import com.everymeal.presentation.ui.whatfood.WhatFoodScreen
import com.everymeal.presentation.ui.withdraw.WithDrawScreen
import com.everymeal.presentation.util.sharedViewModel

const val DETAIL_SCREEN_TYPE = "detailScreenType"
const val DETAIL_RESTAURANT_IDX = "detailRestaurantIdx"

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    networkPreference: NetworkPreference,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var bottomBarState by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            if (bottomBarState) {
                EveryMealBottomNavigation(
                    currentDestination = currentDestination,
                    navigateToScreen = { navigationItem ->
                        navigateBottomNavigationScreen(
                            navController = navController,
                            navigationItem = navigationItem,
                        )
                    },
                )
            }
        },
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = EveryMealRoute.HOME.route,
        ) {
            composable(route = EveryMealRoute.HOME.route) {
                HomeScreen(
                    networkPreference = networkPreference,
                    onDetailScreenClickType = { detailScreenType ->
                        navController.navigate(EveryMealRoute.DETAIL_LIST.route.plus("/$detailScreenType"))
                    },
                    onDetailRestaurantClick = { detailRestaurantIdx ->
                        navController.navigate(EveryMealRoute.DETAIL_RESTAURANT.route.plus("/$detailRestaurantIdx"))
                    },
                    onReviewBottomSheetClick = {
                        navController.navigate(EveryMealRoute.SCHOOL_AUTH.route)
                    },
                    moveToReviewScreen = {
                        navController.navigate(EveryMealRoute.REVIEW_SEARCH.route)
                    }
                )
            }
            composable(route = EveryMealRoute.UNIV_FOOD.route) {
                UnivFoodScreen()
            }
            composable(route = EveryMealRoute.WHAT_FOOD.route) {
                WhatFoodScreen()
            }
            composable(route = EveryMealRoute.MY_PAGE.route) {
                MyPageScreen(
                    withDrawClick = {
                        navController.navigate(EveryMealRoute.WITH_DRAW.route)
                    }
                )
            }
            composable(route = EveryMealRoute.DETAIL_LIST.route.plus("/{$DETAIL_SCREEN_TYPE}")) {
                val detailScreenType = it.arguments?.getString(DETAIL_SCREEN_TYPE) ?: ""
                DetailListScreen(
                    title = detailScreenType,
                    navigateToPreviousScreen = { navController.popBackStack() },
                    onDetailRestaurantClick = { detailRestaurantIdx ->
                        navController.navigate(EveryMealRoute.DETAIL_RESTAURANT.route.plus("/$detailRestaurantIdx"))
                    }
                )
            }
            composable(route = EveryMealRoute.DETAIL_RESTAURANT.route.plus("/{$DETAIL_RESTAURANT_IDX}")) {
                val detailRestaurantIdx = it.arguments?.getString(DETAIL_RESTAURANT_IDX) ?: ""
                DetailRestaurantScreen(
                    restaurantId = detailRestaurantIdx.toInt(),
                    onNetWorkErrorCancelClick = { navController.popBackStack() },
                    backButtonClick = { navController.popBackStack() }
                )
            }
            composable(route = EveryMealRoute.SCHOOL_AUTH.route) {
                SchoolAuthScreen(
                    onSuccessEmailVerification = { emailAuthValue, emailAuthToken ->
                        val route =
                            "profile_generate?emailAuthValue=${Uri.encode(emailAuthValue)}&emailAuthToken=${
                                Uri.encode(emailAuthToken)
                            }"
                        navController.navigate(route) {
                            popUpTo(EveryMealRoute.SCHOOL_AUTH.route) {
                                inclusive = true
                            }
                        }
                    },
                )
            }

            composable(route = EveryMealRoute.PROFILE_GENERATE.route) {
                ProfileGenerateScreen(navController = navController)
            }

            composable(route = EveryMealRoute.REVIEW_SEARCH.route) {
                ReviewSearchScreen(navController = navController)
            }
            composable(route = EveryMealRoute.REVIEW.route.plus("/{$DETAIL_RESTAURANT_IDX}")) { navBackStackEntry ->
                val argument = navBackStackEntry.arguments
                val restaurantIdx = argument?.getString(DETAIL_RESTAURANT_IDX).orEmpty()
                val viewModel = navBackStackEntry.sharedViewModel<ReviewViewModel>(
                    navController = navController,
                    navGraphRoute = EveryMealRoute.HOME.route
                )
                ReviewScreen(
                    viewModel = viewModel,
                    restaurantIdx = restaurantIdx,
                    moveReviewWriteScreen = {
                        navController.navigate(EveryMealRoute.REVIEW_WRITE.route)
                    }
                )
            }
            composable(route = EveryMealRoute.WITH_DRAW.route) {
                WithDrawScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
            composable(route = EveryMealRoute.SEARCH.route) {
                SearchScreen(
                    moveReviewScreen = { restaurantIdx ->
                        navController.navigate(EveryMealRoute.REVIEW.route.plus("/$restaurantIdx"))
                    })
            }
            composable(route = EveryMealRoute.REVIEW_WRITE.route) { navBackStackEntry ->
                val viewModel = navBackStackEntry.sharedViewModel<ReviewViewModel>(
                    navController = navController,
                    navGraphRoute = EveryMealRoute.HOME.route
                )
                ReviewWriteScreen(viewModel = viewModel)
            }
        }
    }

    bottomBarState = when (currentDestination?.route) {
        EveryMealRoute.HOME.route -> true
        EveryMealRoute.UNIV_FOOD.route -> true
        EveryMealRoute.WHAT_FOOD.route -> true
        EveryMealRoute.MY_PAGE.route -> true
        else -> false
    }
}
