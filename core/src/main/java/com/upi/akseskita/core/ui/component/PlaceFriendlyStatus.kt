package com.upi.akseskita.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upi.akseskita.core.R

@Composable
fun PlaceFriendlyStatus(
    icon: Int,
    status: Int,
    disableType: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (status) {
        1 -> {
            colorResource(id = R.color.primary)
        }

        2 -> {
            colorResource(id = R.color.warning)
        }

        3 -> {
            colorResource(id = R.color.accent)
        }

        else -> {
            Color.White
        }
    }

    val descriptions = listOf(
        "",
        "sangat ramah $disableType",
        "kurang ramah $disableType",
        "tidak ramah $disableType",
    )

    Box(
        modifier = modifier
            .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(backgroundColor, RoundedCornerShape(5.dp))
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = descriptions[status],
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(40.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Tempat ini " + descriptions[status],
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}