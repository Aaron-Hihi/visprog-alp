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
import com.aaron.walkcore.ui.view.screen.LoginScreen
import com.aaron.walkcore.ui.view.screen.ProfileScreen
import com.aaron.walkcore.ui.view.screen.RegisterScreen
import com.aaron.walkcore.ui.view.screen.SessionAddScreen
import com.aaron.walkcore.ui.view.screen.SessionDetailScreen

// Definitions for all accessible screens and their associated icons
enum class AppView(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
) {
    LOGIN(title = "Login"),
    REGISTER(title = "Register"),
    HOME(title = "Home", icon = Icons.Filled.Home),
    BROWSE(title = "Browse", icon = Icons.Filled.Search),
    CREATE_SESSION(title = "Add Session", icon = Icons.Filled.Add),
    SCHEDULE(title = "Schedule", icon = Icons.Filled.CalendarMonth),
    PROFILE(title = "Profile", icon = Icons.Filled.Person),
    SESSION_DETAILS(title = "Session Details")
}

// Structure for bottom navigation bar items
data class BottomNavItem(val view: AppView, val label: String)

// Main navigation controller managing the app's backstack and layout shell
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
        BottomNavItem(view = AppView.CREATE_SESSION, label = "Add"),
        BottomNavItem(view = AppView.SCHEDULE, label = "Schedule"),
        BottomNavItem(view = AppView.PROFILE, label = "Profile")
    )

    val currentView = AppView.entries.find { view ->
        currentRoute?.startsWith(view.name) == true
    } ?: AppView.HOME

    Scaffold(
        topBar = {
            // Persistent top navigation bar with back navigation support
            AppTopNav(
                currentView = currentView,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            // Conditional rendering of bottom navigation bar based on route
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
            startDestination = AppView.LOGIN.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Authenticate existing users
            composable(route = AppView.LOGIN.name) {
                LoginScreen(navController = navController)
            }

            // Register new user accounts
            composable(route = AppView.REGISTER.name) {
                RegisterScreen(navController = navController)
            }

            // Main activity overview and statistics
            composable(route = AppView.HOME.name) {
                HomeScreen(navController = navController)
            }

            // User account settings and profile information
            composable(route = AppView.PROFILE.name) {
                ProfileScreen()
            }

            // Form to launch and host a new walking session
            composable(route = AppView.CREATE_SESSION.name) {
                SessionAddScreen(navController = navController)
            }

            // Detailed view of a specific session with ID parameter
            composable(
                route = "${AppView.SESSION_DETAILS.name}/{sessionId}",
                arguments = listOf(
                    navArgument("sessionId") {
                        type = NavType.StringType
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                val sessionId = backStackEntry.arguments?.getString("sessionId") ?: ""
                SessionDetailScreen(
                    sessionId = sessionId,
                    navController = navController
                )
            }
        }
    }
}