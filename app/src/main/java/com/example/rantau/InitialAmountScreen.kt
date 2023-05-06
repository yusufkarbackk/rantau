package com.example.rantau

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.rantau.model.RantauUser

@Composable
fun InitialAmountScreen(
    navController: NavHostController,
    userData: UserData?
): Unit {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var amount by rememberSaveable { mutableStateOf("") }

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)
        ) {
            Button(
                onClick = {
                    val data = RantauUser(
                        amount = amount.toInt(),
                        userId = userData!!.userId
                    )
                    RantauUserViewModel().addInitialUser(data)
//                    RantauUserServices().getUserBalance(userData.userId)
                    navController.navigate(RantauScreens.Home.name)
                },
            ) {
                Text("Save")
            }
        }
    }
}