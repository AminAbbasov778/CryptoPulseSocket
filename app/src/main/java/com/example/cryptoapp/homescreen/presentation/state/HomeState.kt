package com.example.cryptoapp.homescreen.presentation.state

import com.example.cryptoapp.core.presentation.base.UIState
import com.example.cryptoapp.data.presentation.models.CryptoUiModel

data class HomeState (val cryptoList: List<CryptoUiModel> = emptyList()): UIState {
}