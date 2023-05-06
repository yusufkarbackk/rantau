package com.example.rantau

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.rantau.components.RantauAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    modifier: Modifier,
    userData: UserData?,
    onSignOut: () -> Unit,
    navController: NavHostController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(RantauScreens.Transaction.name)
                }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Button(
            onClick = {
                println("sign in")
                onSignOut()
            }
        ) {
            Text("Sign out")
        }
    }


}