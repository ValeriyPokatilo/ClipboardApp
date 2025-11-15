package app.xl.clipboardapp.navigation

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.xl.clipboardapp.screens.ClipboardScreen
import app.xl.clipboardapp.screens.DetailsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Clipboard.route
    ) {
        composable(Screen.Clipboard.route) {
            ClipboardScreen(
                navigateToDetails = { id ->
                    Log.d("test", "$it")
                    navController.navigate(Screen.Details.createRoute(id))
                }
            )
        }
        composable(Screen.Details.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailsScreen(id = id)
        }
    }
}

sealed class Screen(val route: String) {
    data object Clipboard: Screen("clipboard")
    data object Details: Screen("details/{id}") {
        fun createRoute(id: Int?): String {
            return "details/${id}"
        }
        fun getId(arguments: Bundle?): String? {
            return arguments?.getString("id")
        }
    }
}