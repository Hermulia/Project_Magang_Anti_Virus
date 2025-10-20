package com.riset.antivirus.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.riset.antivirus.screen.MainScreen
import com.riset.antivirus.screen.ScanHistoryScreen
import com.riset.antivirus.screen.ScanResultScreen

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object History : Screen("scan_history_screen")
    object Result : Screen("scan_result_screen/{result}") {
        fun createRoute(result: String) = "scan_result_screen/$result"
    }
}

@Composable
//fun Navigation(navController: NavHostController = rememberNavController()) {
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Main.route,
//        modifier = Modifier
//    ) {
//        composable(Screen.Main.route) {
//            MainScreen(navController)
//        }
//        composable(Screen.History.route) {
//            ScanHistoryScreen(navController)
//        }
//        composable(Screen.Result.route) { backStackEntry ->
//            val result = backStackEntry.arguments?.getString("result") ?: "No Result"
//            ScanResultScreen(result, navController)
//        }
//    }
//}

fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = Modifier
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.History.route) {
            ScanHistoryScreen(navController)
        }
        composable(Screen.Result.route) { backStackEntry ->
            val result = backStackEntry.arguments?.getString("result") ?: "No Result"
            ScanResultScreen(result, navController)
        }
    }
}

