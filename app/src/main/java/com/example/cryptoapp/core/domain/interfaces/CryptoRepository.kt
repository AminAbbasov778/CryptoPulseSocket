package com.example.cryptoapp.domain.interfaces

import com.example.cryptoapp.domain.models.CryptoDomainModel
import com.example.cryptoapp.domain.models.CryptoUpdateModel
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    fun cryptoDetailUpdates(): Flow<Result<CryptoDomainModel>>
    fun cryptoListUpdates():  Flow<Result<List<CryptoDomainModel>>>
    fun mapUpdateToCryptoModel(update: CryptoUpdateModel, oldModel: CryptoDomainModel?): CryptoDomainModel

    fun getDefaultCryptoList(): List<CryptoDomainModel>

    fun subscribeDetail(coinName: String)
    fun subscribeCryptoList(coinsList: List<String>)


}