package com.upi.akseskita.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.upi.akseskita.R
import com.upi.akseskita.ui.navigation.NavigationItem
import com.upi.akseskita.ui.navigation.Screen

@Composable
fun BottomBar(
    navHostController: NavHostController
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItem = listOf(
        NavigationItem(
            "Home",
            Icons.Outlined.Home,
            Icons.Default.Home,
            Screen.HomeScreen
        ),
        NavigationItem(
            "Maps",
            Icons.Outlined.Place,
            Icons.Default.Place,
            Screen.Maps
        ),
        NavigationItem(
            "Favorite",
            Icons.Outlined.FavoriteBorder,
            Icons.Outlined.Favorite,
            Screen.Favorite
        ),
        NavigationItem(
            "Profile",
            Icons.Outlined.Person,
            Icons.Default.Person,
            Screen.Profile
        ),
    )

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.background(Color.Transparent)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(colorResource(id = R.color.primary))
                .border(
                    BorderStroke(1.dp, Color.Black),
                    shape = RectangleShape
                )
                .padding(top = 1.dp)
                .height(64.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                navigationItem.forEachIndexed { index, navigationItem ->
                    if (index == 2) {
                        Spacer(modifier = Modifier.width(50.dp))
                    }
                    IconWithText(
                        icon = if (currentRoute == navigationItem.screen.route) navigationItem.iconActive else navigationItem.iconInactive,
                        text = navigationItem.title,
                        onClick = {
                            navHostController.navigate(navigationItem.screen.route) {
                                popUpTo(navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        })
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(colorResource(id = R.color.accent), shape = CircleShape)
                    .border(1.dp, Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = { /* Action for this button */ },
                        modifier = Modifier.size(22.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_robot),
                            contentDescription = "Icon Robot",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(28.dp)
                        )
                    }
                    Text(
                        text = "AI",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Composable
fun IconWithText(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(58.dp)
    ) {
        IconButton(
            onClick = { onClick() },
            modifier = Modifier.size(22.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.Black,
                modifier = Modifier.size(22.dp)
            )
        }
        Text(
            text = text,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            fontSize = 12.sp,
            lineHeight = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(rememberNavController())
}