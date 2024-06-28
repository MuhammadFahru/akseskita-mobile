package com.upi.akseskita.feature.favorite

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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.upi.akseskita.core.R
import com.upi.akseskita.core.domain.model.PlaceModel
import com.upi.akseskita.core.ui.UiState
import com.upi.akseskita.core.ui.component.PlaceItem
import com.upi.akseskita.core.ui.component.TextFieldWithMic
import org.koin.androidx.compose.koinViewModel

@Composable
fun Favorite(
    navigateToDetail: (Int) -> Unit,
    viewModel: FavoriteViewModel = koinViewModel()
) {
    val allFacilitiesState by viewModel.allFacilitiesState.collectAsState()
    var search by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(Unit) {
       viewModel.getAllFacilities()
    }

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
                when(val state = allFacilitiesState) {
                    is UiState.Success -> {
                        items(state.data) {
                            PlaceItem(
                                name = it.name ?: "",
                                category = it.category ?: "",
                                location = it.location ?: "",
                                rating = it.rating?.toFloat() ?: 0f,
                                imageUrl = it.imageUrl.first(),
                                isFavorite = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(216.dp)
                                    .clickable {
                                        navigateToDetail(it.id ?: 0)
                                    }
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                    is UiState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )
                            }
                        }
                    }
                    is UiState.Error -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = "Terjadi Kesalahan: ${state.exception}",
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}