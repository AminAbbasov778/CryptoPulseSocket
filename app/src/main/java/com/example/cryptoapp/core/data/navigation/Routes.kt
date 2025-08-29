package com.example.cryptoapp.data.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Routes {
    @Serializable
   data object HomeScreenRoot : Routes()

    @Serializable
   data object HomeScreen : Routes()

    @Serializable
    data class DetailScreen(val cryptoName: String) : Routes()
}
