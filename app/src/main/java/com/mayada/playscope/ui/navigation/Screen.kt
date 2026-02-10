package com.mayada.playscope.ui.navigation

sealed class Screen(val route: String) {
    object Games : Screen("games")
    object Details : Screen("details/{gameId}") {
        fun createRoute(gameId: Int): String {
            return "details/$gameId"
        }
    }
}
