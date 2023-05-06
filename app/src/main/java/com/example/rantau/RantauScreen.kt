package com.example.rantau

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rantau.ui.theme.RantauTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rantau.components.RantauAppBar
import com.example.rantau.ui.theme.ChartScreen
import com.google.android.gms.auth.api.identity.Identity


//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            RantauTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    RantauApp()
//                }
//            }
//        }
//    }
//}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RantauApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userData: UserData?
) {
    Scaffold(
        bottomBar = {
            RantauAppBar(modifier, navController)
        },
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
        HomeScreen(userData = userData)
    }
//        Column {
//            Header()
//            MainContent()
//            SpendingContent()
//        }
}
