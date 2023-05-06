package com.example.rantau

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    userData: UserData?,
) {
    Scaffold(

    ) {
        Column {
            Header(username = userData?.username)
            MainContent(userId = userData!!.userId)
            SpendingContent()
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier, username: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier.padding(
                top = 12.dp,
                bottom = 12.dp
            )
        ) {
            Text(
                "Welcome back,",
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                modifier = modifier.padding(top = 12.dp, bottom = 8.dp)
            )
            if (username != null) {
                Text(username, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }

        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    viewModel: RantauUserViewModel = RantauUserViewModel(),
    userId: String
) {
//    viewModel.getRantauUser(userId)
    val user = viewModel.user.value ?: return
    Column(
        modifier.padding(12.dp)

    ) {
        Card(
            backgroundColor = Color.Green,
            shape = RoundedCornerShape(25),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier.padding(12.dp)
            ) {
                Text(
                    "My balance,",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
                )
                Text("Rp${user.amount}", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
        }
        Spacer(Modifier.padding(8.dp))
        Card(
            backgroundColor = Color.Red,
            shape = RoundedCornerShape(25),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier.padding(12.dp)
            ) {
                Text(
                    "Spent,",
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    modifier = modifier.padding(top = 12.dp, bottom = 8.dp)
                )
                Text("Rp${user.amount}", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 24.sp)

            }
        }
    }
}

@Composable
fun SpendingContent(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(12.dp)
    ) {
        Text("Spending", fontWeight = FontWeight.SemiBold)
        Text("See all", fontWeight = FontWeight.Light, color = Color.LightGray)
    }
    Spacer(Modifier.padding(10.dp))
    SpendingList(Modifier)

}

@Composable
fun SpendingList(modidier: Modifier = Modifier) {
    Column(
    ) {
        Card(
            shape = RoundedCornerShape(25),
            modifier = Modifier.fillMaxWidth().padding(20.dp).height(70.dp),
            backgroundColor = Color.Red
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null, tint = Color.White)
                    Text("Food", color = Color.White)
                }
                Text("Rp50.000", color = Color.White)
            }
        }
    }
}

