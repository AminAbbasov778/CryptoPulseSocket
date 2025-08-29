package com.example.cryptoapp.detailscreen.presentation.events

import com.example.cryptoapp.core.presentation.base.UIEvent
import com.example.cryptoapp.detailscreen.presentation.navigation.DetailNavigation

sealed class DetailEvents  : UIEvent{
    data class Navigation(val detailNavigation: DetailNavigation) : DetailEvents()
    data class OnGoToEndClicked(val goToEnd : Boolean) : DetailEvents()
    data class OnAutoScrollClicked(val isAutoScrollEnabled : Boolean) : DetailEvents()

}