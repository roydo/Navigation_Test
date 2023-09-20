package com.example.navigationtest.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class Views {
    Home,
    First,
    Second,
    Third
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTestAppBar(
    currentScreen: Views,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = currentScreen.name) },
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of current screen
    val currentScreen = Views.valueOf(
        backStackEntry?.destination?.route ?: Views.Home.name
    )

    Scaffold(
        topBar = {
            NavTestAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) {it ->
        NavHost(
            navController = navController,
            startDestination = Views.Home.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Views.Home.name) {
                CommonView(
                    modifier = modifier,
                    positionString = "Home",
                    toNextView = { navController.navigate(Views.First.name) },
                    toHomeView = {backToHomeView(
                        navController = navController
                    )},
                    backgroundColor = Color.White
                )
            }
            composable(route = Views.First.name) {
                FirstView(
                    onNextButtonClicked = {navController.navigate(Views.Second.name)},
                    toHomeView = {backToHomeView(
                        navController = navController
                    )},
                    modifier = modifier
                )
            }
            composable(route = Views.Second.name) {
                SecondView(
                    onNextButtonClicked = {navController.navigate(Views.Home.name)},
                    toHomeView = {backToHomeView(
                        navController = navController
                    )},
                    modifier = modifier
                )
            }
        }
    }
}

private fun backToHomeView(
    navController: NavController
) {
    navController.popBackStack(Views.Home.name, inclusive = false)
}