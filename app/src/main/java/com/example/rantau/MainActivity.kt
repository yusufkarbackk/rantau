package com.example.rantau

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rantau.model.RantauUser
import com.example.rantau.ui.theme.ChartScreen
import com.example.rantau.ui.theme.RantauTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val googleAuthUIClient by lazy {
        GoogleAuthUIClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    private var destination = RantauScreens.Login.name
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)

        if (auth.currentUser != null) {
            val data = googleAuthUIClient.getSignedInUser()
            RantauUserViewModel().getRantauUser(data!!.userId)
            destination = RantauScreens.Home.name
        }
        setContent {
            RantauTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = destination,
                    ) {
                        composable(route = RantauScreens.Home.name) {
                            RantauApp(
                                navController = navController,
                                userData = googleAuthUIClient.getSignedInUser()
                            )
                        }
                        composable(route = RantauScreens.Chart.name) {
                            ChartScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = RantauScreens.Transaction.name,
                            //arguments = listOf(navArgument("categoryName") { defaultValue = "" })
                        ) {
                            TransactionScreen(
                                navController = navController,
                                userData = googleAuthUIClient.getSignedInUser()
                            )
                        }
                        composable(route = RantauScreens.Category.name) {
                            CategoryScreen(
                                navController = navController
                            )
                        }
                        composable(route = RantauScreens.Initial.name) {
                            InitialAmountScreen(
                                userData = googleAuthUIClient.getSignedInUser(),
                                navController = navController
                            )

                        }
                        composable(route = RantauScreens.Profile.name) {
                            ProfileScreen(
                                userData = googleAuthUIClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUIClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed out",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.popBackStack()
                                    }
                                },
                                modifier = Modifier,
                                navController = navController
                            )
                        }
                        composable(route = RantauScreens.Login.name) {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsState()

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUIClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )
                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    val userData = googleAuthUIClient.getSignedInUser()
                                    val data = RantauUser(
                                        amount = 0,
                                        userId = userData!!.userId
                                    )

                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    RantauUserViewModel().addInitialUser(data)
                                    RantauUserViewModel().getRantauUser(data.userId)
                                    navController.navigate(RantauScreens.Home.name)
                                    viewModel.resetState()
                                }
                            }
                            LoginScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUIClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

