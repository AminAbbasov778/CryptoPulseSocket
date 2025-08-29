package com.example.cryptoapp.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.cryptoapp.core.presentation.base.BaseViewModel
import com.example.cryptoapp.data.navigation.Routes
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun BaseViewModel<*, *, *>.Navigate(navController: NavController) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            navigation.collectLatest {
                if (it.shouldPop) {
                    it.route?.let { route ->
                        navController.popBackStack(route = route, inclusive = it.popInclusive)
                            ?: run {
                                navController.safePopBackStack()
                            }
                    }
                }else{
                    navController.safeNavigate(
                        route = it.route
                            ?: throw IllegalArgumentException("Navigation with shouldPop=false cannot have null route"),
                        popUpToRoute = it.popUpToRoute,
                        inclusive = it.popInclusive,
                        isLaunchSingleTop = it.isLaunchSingleTop
                    )
                }
            }
        }
    }
}

fun NavController.safeNavigate(
    route: Routes,
    popUpToRoute: Routes? = null,
    inclusive: Boolean = false,
    isLaunchSingleTop: Boolean,
) {
    this.navigate(route = route, navOptions {
        launchSingleTop = isLaunchSingleTop
        popUpToRoute?.let {
            popUpTo(it) {
                this.inclusive = inclusive
            }
        }
    })
}

fun NavController.safePopBackStack() {
    if (this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        this.popBackStack()
    }
}