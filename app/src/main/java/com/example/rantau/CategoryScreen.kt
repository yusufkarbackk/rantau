package com.example.rantau

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,

    ) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Income", "Expense")
    val incomes = listOf(
        "Work", "Gift",
    )
    val expenses = listOf(
        "Food", "Entertainment", "Fashion", "Transport"
    )

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.Magenta
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    itemsIndexed(incomes) { _, title ->
                        Card(
                            modifier = Modifier.padding(8.dp).fillMaxWidth().clickable {
                                navController.previousBackStackEntry?.arguments?.putString("category", title)
                                if (title in incomes) {
                                    navController.previousBackStackEntry?.arguments?.putBoolean("isIncome", true)
                                } else {
                                    navController.previousBackStackEntry?.arguments?.putBoolean("isIncome", false)
                                }
                                navController.popBackStack()
                            },
                            elevation = 4.dp
                        ) {
                            Text(
                                text = "$title",
                                modifier = Modifier.padding(16.dp),
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }

            1 -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    itemsIndexed(expenses) { _, title ->
                        Card(
                            modifier = Modifier.padding(8.dp).fillMaxWidth().clickable {
                                navController.previousBackStackEntry?.arguments?.putString("category", title)
                                if (title in incomes) {
                                    navController.previousBackStackEntry?.arguments?.putBoolean("isIncome", true)
                                } else {
                                    navController.previousBackStackEntry?.arguments?.putBoolean("isIncome", false)
                                }
                                navController.popBackStack()
                            },
                            elevation = 4.dp
                        ) {
                            Text(
                                text = "$title",
                                modifier = Modifier.padding(16.dp),
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}