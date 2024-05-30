package com.upi.akseskita.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SplashScreen : Screen("splash_screen")
}