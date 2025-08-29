package com.example.cryptoapp.detailscreen.presentation.state

import com.example.cryptoapp.core.presentation.base.UIState
import com.example.cryptoapp.data.presentation.models.CryptoUiModel

data class DetailState(
    val detailState: CryptoUiModel? = null, val subscribedCoins: List<String> = listOf(),
    var goToEnd: Boolean = true,
    var isAutoScrollEnabled : Boolean = false
) : UIState {
}