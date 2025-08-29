package com.example.cryptoapp.data.presentation.mappers

import com.example.cryptoapp.data.presentation.models.CryptoHistoryUi
import com.example.cryptoapp.data.presentation.models.CryptoUiModel
import com.example.cryptoapp.data.presentation.models.CryptoUpdateUi
import com.example.cryptoapp.data.presentation.models.PercentChangeUi
import com.example.cryptoapp.domain.models.CryptoDomainModel
import com.example.cryptoapp.domain.models.CryptoHistoryModel
import com.example.cryptoapp.domain.models.CryptoUpdateModel
import com.example.cryptoapp.domain.models.PercentChangeModel


fun CryptoUpdateModel.toCryptoUpdateUi(): CryptoUpdateUi {
    return CryptoUpdateUi(this.name, this.price, this.timestamp)
}

fun CryptoUpdateUi.toCryptoUpdateModel(): CryptoUpdateModel {
    return CryptoUpdateModel(this.name, this.price, this.timestamp)

}

fun CryptoDomainModel.toCryptoUpdateModel(): CryptoUpdateModel {
    return CryptoUpdateModel(this.cryptoName, this.cryptoPrice, this.timeStamp)


}

fun CryptoDomainModel.toCryptoUiModel(): CryptoUiModel {
    return CryptoUiModel(
        this.cryptoName,
        this.cryptoFullName,
        this.cryptoIcon,
        this.cryptoPrice,
        PercentChangeUi(this.percentChange.value, this.percentChange.isIncreasing),
          this.priceDifference,
        this.changeIcon,
        this.timeStamp,
        this.cryptoHistory.map {
            CryptoHistoryUi(
                it.price,
                PercentChangeUi(it.percentChange.value, it.percentChange.isIncreasing),
                it.hour,
                it.date
            )
        },
    )

}

fun CryptoUiModel.toCryptoDomainModel(): CryptoDomainModel {
    return CryptoDomainModel(
        this.cryptoName,
        this.cryptoFullName,
        this.cryptoIcon,
        this.cryptoPrice,
        priceDifference = this.priceDifference,
        PercentChangeModel(this.percentChange.value, this.percentChange.isIncreasing),

        this.changeIcon,
        this.timeStamp,
        this.cryptoHistory.map { CryptoHistoryModel(it.price,PercentChangeModel(it.percentChange.value, it.percentChange.isIncreasing), it.hour, it.date)  },

    )
}

