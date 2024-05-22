package com.hamzafrd.jetpackgithubusers.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("favorites")
    object Profile : Screen("profile")
    object DetailUser : Screen("home/{userId}") {
        fun createRoute(userId: Long) = "home/$userId"
    }
}
