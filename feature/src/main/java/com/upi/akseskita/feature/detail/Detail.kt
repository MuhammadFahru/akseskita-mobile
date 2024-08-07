package com.upi.akseskita.feature.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.upi.akseskita.core.R
import com.upi.akseskita.core.domain.model.ReviewModel
import com.upi.akseskita.core.ui.UiState
import com.upi.akseskita.core.ui.component.ButtonFill
import com.upi.akseskita.core.ui.component.ButtonOutline
import com.upi.akseskita.core.ui.component.PlaceFriendlyStatus
import com.upi.akseskita.core.ui.component.PlaceReviewItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun Detail(
    id: Int,
    viewModel: DetailViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val detailDataUiState by viewModel.detailDataState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailFacility(id)
    }

    when (val state = detailDataUiState) {
        is UiState.Success -> {
            state.data.let { data ->
                Log.i("DetailData", "$data")
                DetailContent(
                    name = data.name ?: "",
                    category = data.category ?: "",
                    location = data.location ?: "",
                    rating = data.rating?.toFloatOrNull() ?: 0f,
                    imageUrl = data.imageUrl.first(),
                    blindFriendlyStatus = data.tunaNetraFriendlyStatus,
                    disableFriendlyStatus = data.tunaNetraFriendlyStatus,
                    reviewData = data.listReviews ?: emptyList(),
                    navigateBack = navigateBack
                )
            }
        }

        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Terjadi Kesalahan: ${state.exception}",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    category: String,
    location: String,
    rating: Float,
    isFavorite: Boolean = false,
    imageUrl: String,
    blindFriendlyStatus: Int,
    disableFriendlyStatus: Int,
    reviewData: List<ReviewModel>,
    navigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(290.dp)
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
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .wrapContentSize()
                            .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                            .background(Color.White, RoundedCornerShape(5.dp))
                            .clickable {
                                navigateBack()
                            }
                            .padding(9.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Icon Back",
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .wrapContentSize()
                            .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                            .background(Color.White, RoundedCornerShape(5.dp))
                            .padding(9.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Icon Favorite",
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
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
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = String.format("%.1f", rating),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 22.dp, end = 22.dp, bottom = 54.dp)
            ) {
                Column {
                    Text(
                        text = name,
                        fontSize = 36.sp,
                        lineHeight = 38.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = category,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Icon Location",
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = location,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PlaceFriendlyStatus(
                            icon = R.drawable.ic_blind,
                            status = blindFriendlyStatus,
                            disableType = "Tuna Netra",
                            modifier = Modifier.fillMaxWidth(0.5F)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        PlaceFriendlyStatus(
                            icon = R.drawable.ic_disable,
                            status = disableFriendlyStatus,
                            disableType = "Tuna Daksa",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    ButtonOutline(text = "Beri ulasan", onTapAction = {})
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Ulasan",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyColumn {
                        items(reviewData) { data ->
                            PlaceReviewItem(
                                username = data.username ?: "",
                                userType = data.disabilityType ?: "",
                                rating = data.rating?.toInt() ?: 0,
                                review = data.review ?: ""
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                }
            }
        }

        ButtonFill(
            text = "Rute ke tampat ini",
            onTapAction = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 22.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {
    DetailContent(
        name = "Nama Lokasi",
        category = "Kategori",
        location = "Nama Jalan",
        rating = 5.0F,
        imageUrl = "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        blindFriendlyStatus = 2,
        disableFriendlyStatus = 1,
        reviewData = emptyList(),
        navigateBack = {}
    )
}