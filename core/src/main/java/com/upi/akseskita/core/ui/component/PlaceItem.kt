package com.upi.akseskita.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.upi.akseskita.core.R

@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
    name: String,
    category: String,
    location: String,
    rating: Float,
    isFavorite: Boolean = false,
    imageUrl: String,
) {
    Box(
        modifier = modifier
            .border(1.dp, Color.Black, RoundedCornerShape(7.dp))
            .clip(RoundedCornerShape(7.dp))
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .wrapContentSize()
                    .border(1.dp, Color.Black, RoundedCornerShape(7.dp))
                    .background(Color.White, RoundedCornerShape(7.dp))
                    .padding(7.dp, 7.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Icon Rating",
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .wrapContentSize()
                    .border(1.dp, Color.Black, RoundedCornerShape(7.dp))
                    .background(Color.White, RoundedCornerShape(7.dp))
                    .padding(7.dp, 7.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Icon Favorite",
                    modifier = Modifier.size(14.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(1.dp, Color.Black, RoundedCornerShape(7.dp))
                    .background(Color.White, RoundedCornerShape(7.dp))
                    .padding(9.dp)
            ) {
                Column {
                    Text(
                        text = category,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                    Text(
                        text = name,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Icon Location",
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = location,
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceItemPreview() {
    PlaceItem(
        Modifier.size(225.dp, 328.dp),
        "Nama Tempat",
        "Kategori",
        "Lokasi",
        5.0f,
        false,
        "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    )
}