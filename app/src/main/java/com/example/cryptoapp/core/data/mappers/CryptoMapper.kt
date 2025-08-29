package com.example.cryptoapp.data.mappers

import com.example.cryptoapp.R
import com.example.cryptoapp.core.util.CryptoResourceHelper.getFullName
import com.example.cryptoapp.core.util.CryptoResourceHelper.getIcon
import com.example.cryptoapp.core.util.CryptoResourceHelper.getName
import com.example.cryptoapp.data.model.CryptoHistory
import com.example.cryptoapp.data.model.CryptoModel
import com.example.cryptoapp.data.model.CryptoUpdate
import com.example.cryptoapp.data.model.PercentChange
import com.example.cryptoapp.data.utils.parseTimestamp
import com.example.cryptoapp.domain.models.CryptoDomainModel
import com.example.cryptoapp.domain.models.CryptoHistoryModel
import com.example.cryptoapp.domain.models.CryptoUpdateModel
import com.example.cryptoapp.domain.models.PercentChangeModel
import kotlin.math.round


fun CryptoUpdate.toCryptoModel(update: CryptoUpdate, oldModel: CryptoModel?): CryptoModel {
    val history = oldModel?.cryptoHistory.orEmpty().toMutableList()
    val oldPrice = oldModel?.cryptoPrice ?: update.price



    val rawPercent = if (oldPrice != 0.0) ((update.price - oldPrice) / oldPrice) * 100 else 0.0
    val isIncreasing = rawPercent > 0
    val priceDifference = round(kotlin.math.abs(update.price - oldPrice)*1000)/ 1000
    val percentChange = round(rawPercent * 100) / 100
    val (hour, date) = parseTimestamp(update.timestamp)
    history.add(
        CryptoHistory(
           percentChange =PercentChange(percentChange, isIncreasing),
            hour = hour,
            date = date,
            price = update.price
        )
    )
    return CryptoModel(
        cryptoName = getName(update.name),
        cryptoFullName = getFullName(update.name),
        cryptoIcon = getIcon(update.name),
        cryptoPrice = update.price,
        priceDifference = priceDifference,
        percentChange = PercentChange(percentChange, isIncreasing),
        changeIcon = if (isIncreasing) R.drawable.ic_increasing else R.drawable.ic_decreasing,
        cryptoHistory = history,
        timeStamp = update.timestamp
    )
}


fun CryptoUpdate.toCryptoUpdateModel(): CryptoUpdateModel {
    return CryptoUpdateModel(this.name, this.price, this.timestamp)
}

fun CryptoUpdateModel.toCryptoUpdate(): CryptoUpdate{
    return CryptoUpdate(
        this.name,
        this.price,
        this.timestamp
    )
}

fun CryptoModel.toCryptoDomainModel(): CryptoDomainModel{
    return CryptoDomainModel(
        this.cryptoName,
        this.cryptoFullName,
        this.cryptoIcon,
        this.cryptoPrice,
        priceDifference = this.priceDifference,
        PercentChangeModel(this.percentChange.value, this.percentChange.isIncreasing),
        this.changeIcon,
        this.timeStamp,
        this.cryptoHistory.map { CryptoHistoryModel(percentChange = PercentChangeModel(it.percentChange.value, it.percentChange.isIncreasing), price =  it.price, hour =  it.hour, date =  it.date)  },
    )

}
fun CryptoDomainModel.toCryptoModel(): CryptoModel{
    return CryptoModel(this.cryptoName,
        this.cryptoFullName,
        this.cryptoIcon,
        this.cryptoPrice,
      percentChange =  PercentChange(this.percentChange.value, this.percentChange.isIncreasing),
        priceDifference = this.priceDifference,
        this.changeIcon,
        this.timeStamp,
        this.cryptoHistory.map { CryptoHistory(this.cryptoPrice,PercentChange(it.percentChange.value, it.percentChange.isIncreasing), it.hour, it.date)  },
        )
}

