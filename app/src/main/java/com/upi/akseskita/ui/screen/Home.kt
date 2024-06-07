package com.upi.akseskita.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upi.akseskita.R
import com.upi.akseskita.ui.component.BottomBar
import com.upi.akseskita.ui.navigation.Screen

@Composable
fun Home(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navHostController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) { HomeScreen() }
            composable(Screen.Maps.route) { Maps() }
            composable(Screen.Favorite.route) { Favorite() }
            composable(Screen.Profile.route) { Profile() }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Rumah",
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}