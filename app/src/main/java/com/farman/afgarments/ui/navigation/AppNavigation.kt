package com.farman.afgarments.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.farman.afgarments.ui.screens.*

sealed class Screen(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Splash : Screen("splash", "Splash", { })
    object Dashboard : Screen("dashboard", "Dashboard", { Icon(Icons.Default.Home, contentDescription = "Dashboard") })
    object History : Screen("history", "History", { Icon(Icons.Default.List, contentDescription = "History") })
    object Reports : Screen("reports", "Reports", { Icon(Icons.Default.Star, contentDescription = "Reports") })
    object Settings : Screen("settings", "Settings", { Icon(Icons.Default.Settings, contentDescription = "Settings") })
    object AddEntry : Screen("add_entry", "Add Entry", { })
    object WorkerDetail : Screen("worker_detail/{workerName}", "Worker Detail", { }) {
        fun createRoute(workerName: String) = "worker_detail/$workerName"
    }
}

val BottomNavScreens = listOf(
    Screen.Dashboard,
    Screen.History,
    Screen.Reports,
    Screen.Settings
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in BottomNavScreens.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    BottomNavScreens.forEach { screen ->
                        NavigationBarItem(
                            icon = screen.icon,
                            label = { Text(screen.title) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (currentRoute == Screen.Dashboard.route) {
                FloatingActionButton(onClick = { navController.navigate(Screen.AddEntry.route) }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Entry")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) { SplashScreen(navController) }
            composable(Screen.Dashboard.route) { DashboardScreen(navController) }
            composable(Screen.History.route) { HistoryScreen(navController) }
            composable(Screen.Reports.route) { ReportsScreen(navController) }
            composable(Screen.Settings.route) { SettingsScreen(navController) }
            composable(Screen.AddEntry.route) { AddEditEntryScreen(navController) }
            composable(Screen.WorkerDetail.route) { backStackEntry ->
                val workerName = backStackEntry.arguments?.getString("workerName") ?: ""
                WorkerDetailScreen(navController, workerName)
            }
        }
    }
}
