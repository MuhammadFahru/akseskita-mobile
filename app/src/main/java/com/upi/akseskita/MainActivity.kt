package com.upi.akseskita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upi.akseskita.ui.navigation.Screen
import com.upi.akseskita.ui.screen.Home
import com.upi.akseskita.ui.screen.Onboarding
import com.upi.akseskita.ui.screen.Persona
import com.upi.akseskita.ui.screen.SplashScreen
import com.upi.akseskita.ui.theme.AksesKitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AksesKitaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen") {
                    composable(Screen.SplashScreen.route) { SplashScreen(navController = navController) }
                    composable(Screen.Onboarding.route) { Onboarding(navController = navController) }
                    composable(Screen.Persona.route) { Persona(navController = navController) }
                    composable(Screen.Home.route) { Home() }
                }
            }
        }
    }
}