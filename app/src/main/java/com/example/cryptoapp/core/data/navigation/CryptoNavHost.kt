package com.example.cryptoapp.data.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.cryptoapp.core.util.Navigate
import com.example.cryptoapp.detailscreen.presentation.screens.DetailScreen
import com.example.cryptoapp.detailscreen.presentation.viewmodels.DetailViewModel
import com.example.cryptoapp.homescreen.presentation.screens.HomeScreen
import com.example.cryptoapp.homescreen.presentation.viewmodels.HomeViewModel


@SuppressLint("SuspiciousIndentation")
fun NavGraphBuilder.CryptoNavGraph(navController: NavController){
    composable<Routes.HomeScreen>{
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsState()
        viewModel.Navigate(navController)
        HomeScreen(
            state = state,
            onEvent = {
                viewModel.onEvent(it)
            }
        )
    }
    composable<Routes.DetailScreen>{
        val args = it.toRoute<Routes.DetailScreen>()
        val viewModel = hiltViewModel<DetailViewModel>()
        val isLoading by viewModel.isLoading.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getDetailState(args.cryptoName)

        }

        val detailState by viewModel.state.collectAsState()
        viewModel.Navigate(navController)
        DetailScreen(
            state = detailState,
           onEvents = {
               viewModel.onEvent(it)
           },isLoading = isLoading

        )

    }
}