package com.example.grokapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grokapp.ui.login.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(onLoginClicked = {navController.navigate(Routes.HOME) })
        }
        composable(Routes.HOME){
            //home screen eklenecek birde popbackstack ki geriye basınca logine dönmesin
        }
    }
}