package com.mayada.playscope.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mayada.playscope.ui.screens.GameDetailsScreen
import com.mayada.playscope.ui.screens.GamesScreen

@Composable
fun GameHubNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Games.route
    ) {

        composable(Screen.Games.route) {
            GamesScreen(
                onGameClick = { gameId ->
                    navController.navigate(
                        Screen.Details.createRoute(gameId)
                    )
                }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.IntType
                }
            )
        ) {
            GameDetailsScreen()
        }
    }
}
