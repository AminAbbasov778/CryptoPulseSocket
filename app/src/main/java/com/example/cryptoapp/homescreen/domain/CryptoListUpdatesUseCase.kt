package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.interfaces.CryptoRepository
import javax.inject.Inject

class CryptoListUpdatesUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    operator fun invoke() = cryptoRepository.cryptoListUpdates()
}