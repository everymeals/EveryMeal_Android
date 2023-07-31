package com.everymeal.presentation.ui.bottom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.Paddings

@Composable
fun EveryMealBottomNavigation(
    currentDestination: NavDestination?,
    navigateToScreen: (BottomNavigation) -> Unit,
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
            .border(
                width = 1.dp,
                color = Gray300,
                shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
        tonalElevation = Paddings.xextra
    ) {
        BottomNavigation.values().forEach { bottomItem ->
            NavigationBarItem(
                icon = {
                    Icon(
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
                onClick = { navigateToScreen(bottomItem) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.White),
            )
        }
    }
}

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

@Preview
@Composable
fun PreviewEveryMealBottomNavigation() {
    EveryMealBottomNavigation(
        currentDestination = null,
        navigateToScreen = {}
    )
}