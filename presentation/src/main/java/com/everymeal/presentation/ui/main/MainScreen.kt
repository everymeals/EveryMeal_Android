package com.everymeal.presentation.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.Paddings

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {

}

@Composable
fun EveryMealBottomNavigation(
    currentDestination: NavDestination?,
    navigateToScreen: (BottomNavigation) -> Unit,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        tonalElevation = Paddings.large
    ) {
        BottomNavigation.values().forEach { bottomItem ->
            NavigationBarItem(
                modifier = Modifier.padding(8.dp),
                icon = {
                    Icon(
                        modifier = Modifier.padding(8.dp),
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