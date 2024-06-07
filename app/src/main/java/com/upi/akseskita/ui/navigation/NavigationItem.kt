package com.upi.akseskita.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val iconInactive: ImageVector,
    val iconActive: ImageVector,
    val screen: Screen
)