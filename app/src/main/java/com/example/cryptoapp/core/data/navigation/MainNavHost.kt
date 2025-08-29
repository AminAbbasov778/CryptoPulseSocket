package com.example.cryptoapp.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation

@Composable
fun MainNavHost(navController: NavHostController, startDestination: Routes) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation<Routes.HomeScreenRoot>(startDestination = Routes.HomeScreen) {
            CryptoNavGraph(navController)
        }
    }

}