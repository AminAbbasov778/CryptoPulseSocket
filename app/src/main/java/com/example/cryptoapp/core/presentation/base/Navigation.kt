package com.example.cryptoapp.core.presentation.base

import com.example.cryptoapp.data.navigation.Routes


open class Navigation(
    val route: Routes,
    val shouldPop: Boolean = false,
    val popInclusive: Boolean = false,
    val popUpToInclusive: Boolean = false,
    val popUpToRoute: Routes? = null,
    val isLaunchSingleTop: Boolean = true
) {
}