package com.everymeal.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.everymeal.presentation.ui.bottom.BottomNavigation
import com.everymeal.presentation.ui.bottom.EveryMealBottomNavigation
import com.everymeal.presentation.ui.bottom.navigateBottomNavigationScreen
import com.everymeal.presentation.ui.home.HomeScreen
import com.everymeal.presentation.ui.mypage.MyPageScreen
import com.everymeal.presentation.ui.univfood.UnivFoodScreen
import com.everymeal.presentation.ui.whatfood.WhatFoodScreen

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
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
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = BottomNavigation.HOME.route,
        ) {
            composable(route = BottomNavigation.HOME.route) {
                HomeScreen()
            }
            composable(route = BottomNavigation.UNIV_FOOD.route) {
                UnivFoodScreen()
            }
            composable(route = BottomNavigation.WHAT_FOOD.route) {
                WhatFoodScreen()
            }
            composable(route = BottomNavigation.MY_PAGE.route) {
                MyPageScreen()
            }
        }
    }
}