package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.data.repository.CryptoRepositoryImpl
import javax.inject.Inject

class GetCryptosUseCase @Inject constructor(private val repository: CryptoRepositoryImpl) {
    operator fun invoke() = repository.getDefaultCryptoList()


}