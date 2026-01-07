package com.aaron.walkcore.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aaron.walkcore.ui.view.component.AppBottomNav
import com.aaron.walkcore.ui.view.component.AppTopNav
import com.aaron.walkcore.ui.view.screen.HomeScreen
import com.aaron.walkcore.ui.view.screen.ProfileScreen
import com.aaron.walkcore.ui.view.screen.SessionDetailsScreen

enum class AppView(
    val title: String,
    val icon: ImageVector? = null,
) {
    // Bottom Nav
    HOME(title = "Home", icon = Icons.Filled.Home),
    BROWSE(title = "Browse", icon = Icons.Filled.Search),
    ADD_SESSION(title = "Add Session", icon = Icons.Filled.Add),
    SCHEDULE(title = "Schedule", icon = Icons.Filled.CalendarMonth),
    PROFILE(title = "Profile", icon = Icons.Filled.Person),

    // Others
    SESSION_DETAILS(title = "Session Details")
}

data class BottomNavItem(val view: AppView, val label: String)


@Composable
fun AppRouting(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    val bottomNavItems = listOf(
        BottomNavItem(view = AppView.HOME, label = "Home"),
        BottomNavItem(view = AppView.BROWSE, label = "Browse"),
        BottomNavItem(view = AppView.ADD_SESSION, label = "Add"),
        BottomNavItem(view = AppView.SCHEDULE, label = "Schedule"),
        BottomNavItem(view = AppView.PROFILE, label = "Profile")
    )

    val currentView = bottomNavItems.find { it.view.name == currentRoute }?.view
        ?: AppView.HOME

    Scaffold(
        topBar = {
            AppTopNav(
                currentView = currentView,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },

        bottomBar = {
            if (bottomNavItems.any { it.view.name == currentRoute }) {
                AppBottomNav(
                    navController = navController,
                    currentDestination = currentDestination,
                    items = bottomNavItems
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppView.HOME.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Bottom Nav
            composable(route = AppView.HOME.name) { HomeScreen(navController = navController) }
//            composable(route = AppView.BROWSE.name) { BrowseScreen() }
//            composable(route = AppView.ADD_SESSION.name) { AddSessionScreen() }
//            composable(route = AppView.SCHEDULE.name) { ScheduleScreen() }
            composable(route = AppView.PROFILE.name) { ProfileScreen() }

            // Others
            composable(
                route = AppView.SESSION_DETAILS.name + "/{id}",
                arguments = listOf()
            ) { backStackEntry ->
                SessionDetailsScreen(sessionId = id)
            }
        }
    }
}