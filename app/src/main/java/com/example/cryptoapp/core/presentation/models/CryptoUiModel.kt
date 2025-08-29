package com.example.cryptoapp.data.presentation.models

class CryptoUiModel( val cryptoName: String,
                     val cryptoFullName: String,
                     val cryptoIcon: Int,
                     val cryptoPrice: Double,
                     val percentChange: PercentChangeUi,
                     val priceDifference : Double,
                     val changeIcon: Int? = null,
                     val timeStamp: String = "--:--",
                     val cryptoHistory: List<CryptoHistoryUi> = emptyList()) {
}