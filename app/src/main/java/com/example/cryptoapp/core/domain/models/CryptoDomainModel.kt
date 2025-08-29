package com.example.cryptoapp.domain.models

class CryptoDomainModel( val cryptoName: String,
                         val cryptoFullName: String,
                         val cryptoIcon: Int,
                         val cryptoPrice: Double,
                         val priceDifference : Double,
                         val percentChange: PercentChangeModel,
                         val changeIcon: Int? = null,
                         val timeStamp: String = "--:--",
                         val cryptoHistory: List<CryptoHistoryModel> = emptyList()) {
}