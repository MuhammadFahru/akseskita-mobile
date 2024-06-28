package com.upi.akseskita.feature.maps

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.upi.akseskita.core.R
import com.upi.akseskita.core.ui.UiState
import com.upi.akseskita.core.ui.component.PlaceItem
import com.upi.akseskita.core.ui.component.TextFieldWithMic
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Maps(
    viewModel: MapsViewModel = koinViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val topRatedFacilitiesState by viewModel.topRatedFacilitiesState.collectAsState()
    val context = LocalContext.current
    var search by remember { mutableStateOf(TextFieldValue("")) }
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    // Initialize FusedLocationProviderClient
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Permissions
    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(Unit) {
        // Check permissions
        permissions.launchMultiplePermissionRequest()
        if (permissions.allPermissionsGranted) {
            getCurrentLocation(fusedLocationClient) { location ->
                currentLocation = LatLng(location.latitude, location.longitude)
                cameraPositionState.move(
                    CameraUpdateFactory.newLatLngZoom(
                        currentLocation ?: LatLng(0.0, 0.0), 18f
                    )
                )
            }
        } else {
            Log.e("Maps", "Location permissions are not granted.")
        }
        viewModel.getTopRatedFacilities()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = MapUiSettings(
                compassEnabled = false,
                mapToolbarEnabled = false,
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false,
            ),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = permissions.allPermissionsGranted)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
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

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                        .background(Color.White, RoundedCornerShape(5.dp))
                        .clickable {
                            cameraPositionState.move(
                                CameraUpdateFactory.newLatLngZoom(
                                    currentLocation ?: LatLng(0.0, 0.0), 18f
                                )
                            )
                        }
                        .padding(9.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_my_location_24),
                        contentDescription = "Icon My Location",
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow {
                    when(val state = topRatedFacilitiesState) {
                        is UiState.Success -> {
                            items(state.data) {
                                PlaceItem(
                                    name = it.name ?: "",
                                    category = it.category ?: "",
                                    location = it.location ?: "",
                                    rating = it.rating?.toFloat() ?: 0f,
                                    imageUrl = it.imageUrl.first(),
                                    modifier = Modifier
                                        .size(225.dp, 172.dp)
                                        .clickable { navigateToDetail(it.id ?: 0) }
                                )
                                Spacer(modifier = Modifier.width(7.dp))
                            }
                        }
                        is UiState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(172.dp)
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
                                        .fillMaxWidth()
                                        .height(172.dp)
                                ) {
                                    Text(
                                        text = "Terjadi Kesalahan: ${state.exception}",
                                        fontSize = 18.sp,
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

@SuppressLint("MissingPermission")
private fun getCurrentLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationReceived: (Location) -> Unit
) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let {
            onLocationReceived(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MapsPreview() {
    Maps {}
}