package com.upi.akseskita.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upi.akseskita.core.R

@Composable
fun ButtonOutline(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onTapAction: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent, Color.Black),
        border = BorderStroke(1.dp, Color.Black),
        enabled = enabled,
        onClick = { onTapAction() },
        modifier = modifier
            .height(46.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            fontWeight = FontWeight.ExtraBold,
        )
    }
}