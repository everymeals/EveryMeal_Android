package com.everymeal.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.everymeal.presentation.ui.bottom.BottomNavigation
import com.everymeal.presentation.ui.bottom.EveryMealBottomNavigation
import com.everymeal.presentation.ui.bottom.EveryMealRoute
import com.everymeal.presentation.ui.bottom.navigateBottomNavigationScreen
import com.everymeal.presentation.ui.detail.DetailListScreen
import com.everymeal.presentation.ui.home.HomeScreen
import com.everymeal.presentation.ui.mypage.MyPageScreen
import com.everymeal.presentation.ui.restaurant.DetailRestaurantScreen
import com.everymeal.presentation.ui.univfood.UnivFoodScreen
import com.everymeal.presentation.ui.whatfood.WhatFoodScreen

const val DETAIL_SCREEN_TYPE = "detailScreenType"
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
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
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = EveryMealRoute.HOME.route,
        ) {
            composable(route = EveryMealRoute.HOME.route) {
                HomeScreen(
                    onDetailScreenClickType = { detailScreenType ->
                        navController.navigate(EveryMealRoute.DETAIL_LIST.route.plus("/$detailScreenType"))
                    },
                    onDetailRestaurantClick = {
                        navController.navigate(EveryMealRoute.DETAIL_RESTAURANT.route)
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
                MyPageScreen()
            }
            composable(route = EveryMealRoute.DETAIL_LIST.route.plus("/{$DETAIL_SCREEN_TYPE}"),) {
                val detailScreenType = it.arguments?.getString(DETAIL_SCREEN_TYPE) ?: ""
                DetailListScreen(
                    title = detailScreenType,
                    navigateToPreviousScreen = { navController.popBackStack() }
                )
            }
            composable(route = EveryMealRoute.DETAIL_RESTAURANT.route) {
                DetailRestaurantScreen()
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

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
