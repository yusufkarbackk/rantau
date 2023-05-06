package com.example.rantau.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.rantau.RantauScreens

@Composable
fun RantauAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.White,

        ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = true,
            onClick = {
                navController.navigate(RantauScreens.Home.name)
            },
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.LightGray
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = null) },
            label = { Text("Chart") },
            selected = false,
            onClick = {
                navController.navigate(RantauScreens.Chart.name)
            },
            selectedContentColor = Color.Yellow,
            unselectedContentColor = Color.LightGray
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = null) },
            label = { Text("Recommendation") },
            selected = false,
            onClick = { },
            selectedContentColor = Color.Yellow,
            unselectedContentColor = Color.LightGray
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = null) },
            label = { Text("Profile") },
            selected = false,
            onClick = {
                navController.navigate(RantauScreens.Profile.name)
            },
            selectedContentColor = Color.Yellow,
            unselectedContentColor = Color.LightGray
        )
    }
}