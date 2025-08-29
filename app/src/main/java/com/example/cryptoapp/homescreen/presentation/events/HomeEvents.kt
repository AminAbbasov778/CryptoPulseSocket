package com.example.cryptoapp.homescreen.presentation.events

import com.example.cryptoapp.core.presentation.base.UIEvent
import com.example.cryptoapp.homescreen.presentation.navigation.HomeNavigation

sealed class HomeEvents : UIEvent {
    data class Navigation(val homeNavigation: HomeNavigation) : HomeEvents()

}