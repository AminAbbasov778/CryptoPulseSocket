package com.example.cryptoapp.detailscreen.presentation.navigation

import com.example.cryptoapp.core.presentation.base.Navigation
import com.example.cryptoapp.data.navigation.Routes

sealed class DetailNavigation(routes: Routes, isInclusive: Boolean = false, shouldPop: Boolean = false) : Navigation(routes,shouldPop,isInclusive) {
    data object DetailScreenFromHomeScreen  : DetailNavigation(Routes.HomeScreen)

}