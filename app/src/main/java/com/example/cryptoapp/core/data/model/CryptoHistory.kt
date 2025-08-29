package com.example.cryptoapp.data.model

data class CryptoHistory(
    val price: Double,

    val percentChange : PercentChange,
    val hour: String,
    val date: String,
){
}