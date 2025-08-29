package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.interfaces.CryptoRepository
import com.example.cryptoapp.domain.models.CryptoDomainModel
import com.example.cryptoapp.domain.models.CryptoUpdateModel
import javax.inject.Inject

class MapUpdateToCryptoUseCase @Inject constructor(private val repository: CryptoRepository) {

   operator fun invoke(update: CryptoUpdateModel, oldModel: CryptoDomainModel?): CryptoDomainModel {
       return repository.mapUpdateToCryptoModel(update,oldModel)

   }
}