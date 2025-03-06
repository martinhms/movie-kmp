package org.marton.studio

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.marton.studio.ui.screens.detail.DetailScreen
import org.marton.studio.ui.screens.home.HomeScreen

@Composable
@Preview
fun App() {
    NavigationCompose()
}

@Composable
fun NavigationCompose(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate("detail/${movieId}")
                },
            )
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            DetailScreen(
                movie = movieId ?: "",
                onBack = { navController.popBackStack() }
            )
        }

    }
}

