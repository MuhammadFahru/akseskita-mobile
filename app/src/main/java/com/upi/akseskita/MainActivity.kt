package com.upi.akseskita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    composable("splash_screen") { SplashScreen(navController = navController) }
                    composable("onboarding") { Onboarding(navController = navController) }
                    composable("persona") { Persona(navController = navController) }
                    composable("home") { Home() }
                }
            }
        }
    }
}