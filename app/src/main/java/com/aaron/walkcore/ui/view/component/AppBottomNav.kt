package com.aaron.walkcore.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aaron.walkcore.route.AppView
import com.aaron.walkcore.route.BottomNavItem
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.theme.White

@Composable
fun AppBottomNav(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentDestination: NavDestination?,
    items: List<BottomNavItem>
) {
    // --- View vars ---
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    // Background
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Blue)
    ) {
        // Navigation Bar
        NavigationBar (
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
        ) {

            // Show every item
            items.forEach { item ->
                val route = item.view.name
                val isSelected = currentDestination?.hierarchy?.any { it.route == route } == true

                // Navigation Item
                NavigationBarItem(
                    label = {
                        Text(
                            text = item.label,
                            maxLines = 1,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = item.view.icon!!,
                            contentDescription = item.label
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Blue,
                        selectedTextColor = Blue,
                        unselectedIconColor = White,
                        unselectedTextColor = White,
                        indicatorColor = White
                    )
                )
            }
        }
    }
}