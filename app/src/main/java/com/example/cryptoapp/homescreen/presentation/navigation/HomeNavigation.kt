package com.example.cryptoapp.homescreen.presentation.navigation

import com.example.cryptoapp.core.presentation.base.Navigation
import com.example.cryptoapp.data.navigation.Routes

sealed class HomeNavigation(routes: Routes, isInclusive: Boolean = false, shouldPop: Boolean = false) : Navigation(routes,shouldPop,isInclusive) {
    data class HomeScreenToDetailScreen(val cryptoName: String) : HomeNavigation(Routes.DetailScreen(cryptoName))
}