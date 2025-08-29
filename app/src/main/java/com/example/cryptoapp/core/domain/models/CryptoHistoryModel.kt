package com.example.cryptoapp.domain.models

class CryptoHistoryModel(
    val price: Double,
    val percentChange: PercentChangeModel , val hour: String,
    val date: String,
) {
}