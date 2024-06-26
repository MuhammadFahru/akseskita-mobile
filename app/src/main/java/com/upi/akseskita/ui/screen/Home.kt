package com.upi.akseskita.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.upi.akseskita.R
import com.upi.akseskita.data.model.PlaceModel
import com.upi.akseskita.ui.component.BottomBar
import com.upi.akseskita.ui.component.PlaceItem
import com.upi.akseskita.ui.component.TextFieldWithMic
import com.upi.akseskita.ui.navigation.Screen
import com.upi.akseskita.ui.theme.AksesKitaTheme

@Composable
fun Home(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = { if (currentRoute != Screen.Detail.route) BottomBar(navHostController = navController) }
    ) { innerPadding ->
        AksesKitaTheme {
            var bottomPadding = innerPadding.calculateBottomPadding() - 15.dp
            if (bottomPadding < 0.dp) bottomPadding = 0.dp
            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.route,
                modifier = Modifier.padding(
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    top = innerPadding.calculateTopPadding(),
                    bottom = bottomPadding
                )
            ) {
//                TODO("change to real id, not string")
                composable(Screen.HomeScreen.route) {
                    HomeScreen(navigateToDetail = { placeId ->
                        navController.navigate(
                            Screen.Detail.createRoute(placeId)
                        )
                    })
                }
                composable(Screen.Maps.route) { Maps(navigateToDetail = { placeId ->
                    navController.navigate(
                        Screen.Detail.createRoute(placeId)
                    )
                }) }
                composable(Screen.Favorite.route) { Favorite(navigateToDetail = { placeId ->
                    navController.navigate(
                        Screen.Detail.createRoute(placeId)
                    )
                }) }
                composable(Screen.Profile.route) { Profile() }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument("placeId") { type = NavType.StringType }),
                ) {
                    val id = it.arguments?.getString("placeId") ?: ""
                    Detail(
                        name = id,
                        category = "Kategori",
                        location = "Nama Jalan",
                        rating = 4.0F,
                        imageUrl = "https://images.unsplash.com/photo-1504810935423-dbbe9a698963?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        blindFriendlyStatus = 1,
                        disableFriendlyStatus = 2,
                        navigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    navigateToDetail: (String) -> Unit
) {
    var search by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategoryIndex by remember { mutableIntStateOf(-1) }
    val category = listOf(
        "Fasilitas Umum",
        "Dukungan Kesehatan",
        "Pendidikan",
        "Rekreasi"
    )
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
    )

    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .padding(22.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "Hi! Ujang Kandil",
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Icon Location",
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "Jl. Kenangan",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Kategori",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        itemsIndexed(category) { index, data ->
                            val isSelected = selectedCategoryIndex == index
                            Box(
                                modifier = Modifier
                                    .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                                    .background(
                                        if (isSelected) colorResource(id = R.color.primary) else Color.Transparent,
                                        RoundedCornerShape(5.dp)
                                    )
                                    .clickable { selectedCategoryIndex = index }
                                    .padding(7.dp, 5.dp)
                            ) {
                                Text(
                                    text = data,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rekomendasi Kami",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(placeList) {
                            PlaceItem(
                                name = it.name,
                                category = it.category,
                                location = it.location,
                                rating = it.rating,
                                imageUrl = it.imageUrl,
                                modifier = Modifier
                                    .size(225.dp, 328.dp)
                                    .clickable { navigateToDetail(it.name) }
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Sekitar Kamu",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(placeList) {
                            PlaceItem(
                                name = it.name,
                                category = it.category,
                                location = it.location,
                                rating = it.rating,
                                imageUrl = it.imageUrl,
                                modifier = Modifier.size(225.dp, 328.dp)
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rating Terbaik",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(placeList) {
                            PlaceItem(
                                name = it.name,
                                category = it.category,
                                location = it.location,
                                rating = it.rating,
                                imageUrl = it.imageUrl,
                                modifier = Modifier.size(225.dp, 328.dp)
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen {}
}