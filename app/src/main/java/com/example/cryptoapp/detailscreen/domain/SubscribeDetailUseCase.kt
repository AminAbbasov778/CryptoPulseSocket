package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.interfaces.CryptoRepository
import javax.inject.Inject

class SubscribeDetailUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    operator fun invoke(coinName: String) = cryptoRepository.subscribeDetail(coinName)
}