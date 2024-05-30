package com.upi.akseskita.ui.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upi.akseskita.R

@Composable
fun ButtonFill(
    text: String,
    onTapAction: () -> Unit
) {
    Button(
        modifier = Modifier
            .height(46.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(Color.Black, Color.White),
        onClick = { onTapAction() }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            fontWeight = FontWeight.ExtraBold,

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonFillPreview() {
    ButtonFill(text = "Kenali Diriku", onTapAction = {})
}