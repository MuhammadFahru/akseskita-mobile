package com.upi.akseskita.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.upi.akseskita.R

@Composable
fun Favorite() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Favorite",
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}