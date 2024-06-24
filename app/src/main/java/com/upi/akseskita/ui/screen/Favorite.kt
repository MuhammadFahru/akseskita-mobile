package com.upi.akseskita.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upi.akseskita.R
import com.upi.akseskita.data.model.PlaceModel
import com.upi.akseskita.ui.component.PlaceItem
import com.upi.akseskita.ui.component.TextFieldWithMic

@Composable
fun Favorite(
    navigateToDetail: (String) -> Unit
) {
    var search by remember { mutableStateOf(TextFieldValue("")) }
    val placeList = listOf(
        PlaceModel(
            "Nama Tempat",
            "Kategori",
            "Lokasi",
            5.0f,
            false,
            "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        PlaceModel(
            "Nama Tempat",
            "Kategori",
            "Lokasi",
            5.0f,
            false,
            "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        PlaceModel(
            "Nama Tempat",
            "Kategori",
            "Lokasi",
            5.0f,
            false,
            "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        PlaceModel(
            "Nama Tempat",
            "Kategori",
            "Lokasi",
            5.0f,
            false,
            "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        PlaceModel(
            "Nama Tempat",
            "Kategori",
            "Lokasi",
            5.0f,
            false,
            "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        PlaceModel(
            "Nama Tempat",
            "Kategori",
            "Lokasi",
            5.0f,
            false,
            "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 22.dp, end = 22.dp, top = 22.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "Tempat Favoritmu",
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextFieldWithMic(
                    placeholder = "Stasiun MRT Fatmawati",
                    value = search,
                    modifier = Modifier.weight(0.8f),
                    onValueChange = { search = it },
                    onClickAction = {}
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Icon Search",
                    modifier = Modifier.size(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn {
                items(placeList) {
                    PlaceItem(
                        name = it.name,
                        category = it.category,
                        location = it.location,
                        rating = it.rating,
                        imageUrl = it.imageUrl,
                        isFavorite = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(216.dp)
                            .clickable {
                                navigateToDetail(it.name)
                            }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}