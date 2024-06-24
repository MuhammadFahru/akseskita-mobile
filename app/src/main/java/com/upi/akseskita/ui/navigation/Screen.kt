package com.upi.akseskita.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SplashScreen : Screen("splash_screen")
    object Persona : Screen("persona")
    object Onboarding : Screen("onboarding")
    object HomeScreen : Screen("home_screen")
    object Maps : Screen("maps")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("{placeId}") {
        fun createRoute(placeId: String) = placeId
    }
}