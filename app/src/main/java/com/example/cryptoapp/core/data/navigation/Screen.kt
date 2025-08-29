package com.example.cryptoapp.data.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{coinName}") {
        fun createRoute(coinName: String) = "detail/$coinName"
    }
}