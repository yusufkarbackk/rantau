package com.example.rantau

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rantau.model.Transaction
import com.example.rantau.services.TransactionService

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    navController: NavHostController,
    userData: UserData?
) {
    var category by remember { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var isIncome by remember { mutableStateOf(true) }

    LaunchedEffect(navController) {
        val backStackEntry = navController.currentBackStackEntry
        val savedStateHandle = backStackEntry?.arguments

        savedStateHandle?.getString("category")?.let { value ->
            category = value
        }

        savedStateHandle?.getBoolean("isIncome")?.let { value ->
            isIncome = value
        }
    }
    Scaffold(
    ) {
        Column(
            modifier.padding(12.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = modifier.fillMaxWidth()
            )
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(category)
                Button(
                    onClick = {
                        navController.navigate(RantauScreens.Category.name)
                    },
                ) {
                    Text("Select Category")
                }
            }
            Box(
                modifier = modifier.fillMaxWidth().wrapContentSize(Alignment.Center)
            ) {
                Button(
                    onClick = {
                        val transaction = Transaction(
                            amount.toInt(),
                            category,
                            isIncome,
                            userData!!.userId
                        )
                        TransactionService().addTransaction(transaction, userData.userId)
                        navController.popBackStack()
                    },
                ) {
                    Text("Save")
                }
            }
        }
    }
}