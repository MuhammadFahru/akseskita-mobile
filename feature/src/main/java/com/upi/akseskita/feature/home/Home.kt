package com.upi.akseskita.feature.home

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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.upi.akseskita.core.R
import com.upi.akseskita.core.domain.model.PlaceModel
import com.upi.akseskita.core.ui.UiState
import com.upi.akseskita.core.ui.component.BottomBar
import com.upi.akseskita.core.ui.component.PlaceItem
import com.upi.akseskita.core.ui.component.TextFieldWithMic
import com.upi.akseskita.core.ui.navigation.Screen
import com.upi.akseskita.core.ui.theme.AksesKitaTheme
import com.upi.akseskita.feature.detail.Detail
import com.upi.akseskita.feature.favorite.Favorite
import com.upi.akseskita.feature.maps.Maps
import com.upi.akseskita.feature.profile.Profile
import org.koin.androidx.compose.koinViewModel

@Composable
fun Home(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = koinViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val allFacilitiesUiState by viewModel.allFacilitiesState.collectAsState()
    val topRatedFacilitiesUiState by viewModel.topRatedFacilitiesState.collectAsState()

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
                    HomeScreen(
                        getAllFacilities = viewModel::getAllFacilities,
                        getTopRatedFacilities = viewModel::getTopRatedFacilities,
                        allFacilitiesUiState = allFacilitiesUiState,
                        topRatedFacilitiesUiState = topRatedFacilitiesUiState,
                        navigateToDetail = { placeId ->
                            navController.navigate(
                                Screen.Detail.createRoute(placeId)
                            )
                        }
                    )
                }
                composable(Screen.Maps.route) {
                    Maps(navigateToDetail = { placeId ->
                        navController.navigate(
                            Screen.Detail.createRoute(placeId)
                        )
                    })
                }
                composable(Screen.Favorite.route) {
                    Favorite(navigateToDetail = { placeId ->
                        navController.navigate(
                            Screen.Detail.createRoute(placeId)
                        )
                    })
                }
                composable(Screen.Profile.route) { Profile() }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument("placeId") { type = NavType.IntType }),
                ) {
                    val id = it.arguments?.getInt("placeId") ?: 0
                    Detail(
                        id = id,
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
    navigateToDetail: (Int) -> Unit,
    getAllFacilities: () -> Unit,
    getTopRatedFacilities: () -> Unit,
    allFacilitiesUiState: UiState<List<PlaceModel>>,
    topRatedFacilitiesUiState: UiState<List<PlaceModel>>,
) {
    var search by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategoryIndex by remember { mutableIntStateOf(-1) }
    val category = listOf(
        "Fasilitas Umum",
        "Dukungan Kesehatan",
        "Pendidikan",
        "Rekreasi"
    )

    LaunchedEffect(Unit) {
        getAllFacilities()
        getTopRatedFacilities()
    }

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
                        text = "Selamat Datang",
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
                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        when (allFacilitiesUiState) {
                            is UiState.Loading -> {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .width(400.dp)
                                            .height(328.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.align(
                                                Alignment.Center
                                            )
                                        )
                                    }
                                }
                            }

                            is UiState.Success -> {
                                items(allFacilitiesUiState.data) {
                                    PlaceItem(
                                        name = it.name ?: "",
                                        category = it.category ?: "",
                                        location = it.location ?: "",
                                        rating = it.rating?.toFloat() ?: 0F,
                                        imageUrl = it.imageUrl.first(),
                                        modifier = Modifier
                                            .size(225.dp, 328.dp)
                                            .clickable { navigateToDetail(it.id ?: 0) }
                                    )
                                    Spacer(modifier = Modifier.width(7.dp))
                                }
                            }

                            is UiState.Error -> {
                                item {
                                    Text(
                                        text = "Terjadi kesalahan ${allFacilitiesUiState.exception}",
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rating Terbaik",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        when (topRatedFacilitiesUiState) {
                            is UiState.Loading -> {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .width(600.dp)
                                            .height(328.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.align(
                                                Alignment.Center
                                            )
                                        )
                                    }
                                }
                            }

                            is UiState.Success -> {
                                items(topRatedFacilitiesUiState.data) {
                                    PlaceItem(
                                        name = it.name ?: "",
                                        category = it.category ?: "",
                                        location = it.location ?: "",
                                        rating = it.rating?.toFloat() ?: 0F,
                                        imageUrl = it.imageUrl.first(),
                                        modifier = Modifier
                                            .size(225.dp, 328.dp)
                                            .clickable { navigateToDetail(it.id ?: 0) }
                                    )
                                    Spacer(modifier = Modifier.width(7.dp))
                                }
                            }

                            is UiState.Error -> {
                                item {
                                    Text(
                                        text = "Terjadi kesalahan ${topRatedFacilitiesUiState.exception}",
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}