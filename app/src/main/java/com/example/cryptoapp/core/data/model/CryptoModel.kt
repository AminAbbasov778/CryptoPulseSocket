package com.example.cryptoapp.data.model

data class CryptoModel(
    val cryptoName: String,
    val cryptoFullName: String,
    val cryptoIcon: Int,
    val cryptoPrice: Double,
    val percentChange: PercentChange,
    val priceDifference : Double,
    val changeIcon: Int? = null,
    val timeStamp: String = "--:--",
    val cryptoHistory: List<CryptoHistory> = emptyList()
)
