package com.aaron.walkcore.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.aaron.walkcore.route.BottomNavItem
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.White

@Composable
fun AppBottomNav(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentDestination: NavDestination?,
    items: List<BottomNavItem>
) {
    // Surface used to provide elevation and rounded top corners for elegance
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        color = Blue,
        tonalElevation = 8.dp
    ) {
        // Core navigation container
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            // Iterate through navigation items to build the bar
            items.forEach { item ->
                val route = item.view.name
                val isSelected = currentDestination?.hierarchy?.any { it.route == route } == true

                // Individual interactive navigation node
                NavigationBarItem(
                    label = {
                        Text(
                            text = item.label,
                            maxLines = 1,
                            style = MaterialTheme.typography.labelMedium,
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
                        if (!isSelected) {
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    // Color scheme matching the provided UI reference
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Blue,
                        selectedTextColor = White,
                        unselectedIconColor = White.copy(alpha = 0.7f),
                        unselectedTextColor = White.copy(alpha = 0.7f),
                        indicatorColor = White
                    )
                )
            }
        }
    }
}