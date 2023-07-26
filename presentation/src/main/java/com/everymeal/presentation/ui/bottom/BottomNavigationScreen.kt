package com.everymeal.presentation.ui.bottom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.Paddings

fun navigateBottomNavigationScreen(
    navController: NavHostController,
    navigationItem: BottomNavigation,
) {
    navController.navigate(navigationItem.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun EveryMealBottomNavigation(
    currentDestination: NavDestination?,
    navigateToScreen: (BottomNavigation) -> Unit,
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        tonalElevation = Paddings.xextra
    ) {
        BottomNavigation.values().forEach { bottomItem ->
            NavigationBarItem(
                modifier = Modifier.padding(8.dp),
                icon = {
                    Icon(
                        modifier = Modifier.padding(4.dp),
                        imageVector = ImageVector.vectorResource(bottomItem.icon),
                        contentDescription = bottomItem.route,
                        tint = if (currentDestination?.route == bottomItem.route) {
                            Main100
                        } else {
                            Gray500
                        }
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottomItem.title),
                        color = if (currentDestination?.route == bottomItem.route) {
                            Main100
                        } else {
                            Gray500
                        },
                        style = TextStyle(
                            fontSize = 12.sp,
                        )
                    )
                },
                selected = currentDestination?.route == bottomItem.route,
                onClick = { navigateToScreen(bottomItem) }
            )
        }
    }
}