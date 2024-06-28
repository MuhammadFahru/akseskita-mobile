package com.upi.akseskita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upi.akseskita.core.ui.navigation.Screen
import com.upi.akseskita.core.ui.theme.AksesKitaTheme
import com.upi.akseskita.feature.home.Home
import com.upi.akseskita.feature.onboarding.Onboarding
import com.upi.akseskita.feature.persona.Persona
import com.upi.akseskita.feature.splashscreen.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set app to always use light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            AksesKitaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen") {
                    composable(Screen.SplashScreen.route) {
                        SplashScreen(
                            navController = navController
                        )
                    }
                    composable(Screen.Onboarding.route) {
                        Onboarding(
                            navController = navController
                        )
                    }
                    composable(Screen.Persona.route) {
                        Persona(
                            navController = navController
                        )
                    }
                    composable(Screen.Home.route) { Home() }
                }
            }
        }
    }
}