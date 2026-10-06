package com.example.grokapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grokapp.ui.home.Homescreen
import com.example.grokapp.ui.login.LoginScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = modifier
    ) {
        composable<Routes.LOGIN> {
            LoginScreen(onLoginClicked = { name ->
                navController.navigate(Routes.HOME(name)) {
                    popUpTo(Routes.LOGIN) {
                        inclusive = true
                    }
                }
            })
        }
        composable<Routes.HOME>{
            Homescreen()
        }
    }
}