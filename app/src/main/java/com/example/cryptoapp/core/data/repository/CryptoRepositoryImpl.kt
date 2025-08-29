package com.example.cryptoapp.data.repository

import com.example.cryptoapp.core.util.CryptoResourceHelper
import com.example.cryptoapp.data.mappers.toCryptoDomainModel
import com.example.cryptoapp.data.mappers.toCryptoModel
import com.example.cryptoapp.data.mappers.toCryptoUpdate
import com.example.cryptoapp.data.mappers.toCryptoUpdateModel
import com.example.cryptoapp.data.model.CryptoModel
import com.example.cryptoapp.data.model.PercentChange
import com.example.cryptoapp.data.network.SocketHandler
import com.example.cryptoapp.domain.interfaces.CryptoRepository
import com.example.cryptoapp.domain.models.CryptoDomainModel
import com.example.cryptoapp.domain.models.CryptoUpdateModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val socketHandler: SocketHandler
) : CryptoRepository {


    override fun cryptoListUpdates(): Flow<Result<List<CryptoDomainModel>>> {
        return socketHandler.cryptoListUpdates.map { result ->
            result.map { updates ->
                updates.map { update ->
                    mapUpdateToCryptoModel(update.toCryptoUpdateModel(), null)
                }
            }
        }
    }


    override fun cryptoDetailUpdates(): Flow<Result<CryptoDomainModel>> {
        return socketHandler.cryptoDetailUpdates.map { result ->
            result.map { update ->
                mapUpdateToCryptoModel(update.toCryptoUpdateModel(), null)
            }
        }
    }

    override fun subscribeCryptoList(coinsList: List<String>) {
        socketHandler.subscribeCryptoList(coinsList)
    }

    override fun subscribeDetail(coinName: String) {
        socketHandler.subscribeDetail(coinName)
    }

    override fun mapUpdateToCryptoModel(update: CryptoUpdateModel, oldModel: CryptoDomainModel?): CryptoDomainModel =
        update.toCryptoUpdate().toCryptoModel(update.toCryptoUpdate(), oldModel?.toCryptoModel()).toCryptoDomainModel()

    override fun getDefaultCryptoList(): List<CryptoDomainModel> {
        return listOf("BTC", "DOGE", "BNB", "DOT", "DASH", "NEXO", "AMPL").map { symbol ->
            CryptoModel(
                cryptoName = CryptoResourceHelper.getName(symbol),
                cryptoFullName = CryptoResourceHelper.getFullName(symbol),
                cryptoIcon = CryptoResourceHelper.getIcon(symbol),
                cryptoPrice = 0.0,
                priceDifference = 0.0,
                percentChange = PercentChange(0.0, true),
                cryptoHistory = emptyList()
            ).toCryptoDomainModel()
        }
    }
}
