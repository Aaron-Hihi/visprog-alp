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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aaron.walkcore.ui.view.component.AppBottomNav
import com.aaron.walkcore.ui.view.component.AppTopNav
import com.aaron.walkcore.ui.view.screen.HomeScreen
import com.aaron.walkcore.ui.view.screen.ProfileScreen
import com.aaron.walkcore.ui.view.screen.SessionDetailsScreen

// Enumeration for application views and icons
enum class AppView(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
) {
    HOME(title = "Home", icon = Icons.Filled.Home),
    BROWSE(title = "Browse", icon = Icons.Filled.Search),
    ADD_SESSION(title = "Add Session", icon = Icons.Filled.Add),
    SCHEDULE(title = "Schedule", icon = Icons.Filled.CalendarMonth),
    PROFILE(title = "Profile", icon = Icons.Filled.Person),
    SESSION_DETAILS(title = "Session Details")
}

// Data class for bottom navigation structure
data class BottomNavItem(val view: AppView, val label: String)

// Main navigation host and scaffold layout
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

    val currentView = AppView.entries.find { view ->
        currentRoute?.startsWith(view.name) == true
    } ?: AppView.HOME

    Scaffold(
        topBar = {
            AppTopNav(
                currentView = currentView,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            val isBottomRoute = bottomNavItems.any { it.view.name == currentRoute }
            if (isBottomRoute) {
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
            // Dashboard entry point
            composable(route = AppView.HOME.name) {
                HomeScreen(navController = navController)
            }

            // User profile settings
            composable(route = AppView.PROFILE.name) {
                ProfileScreen()
            }

            // Session details with dynamic string ID parameter
            composable(
                route = "${AppView.SESSION_DETAILS.name}/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                val sessionId = backStackEntry.arguments?.getString("id") ?: ""
                SessionDetailsScreen(sessionId = sessionId)
            }
        }
    }
}