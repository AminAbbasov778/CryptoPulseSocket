package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.interfaces.CryptoRepository
import javax.inject.Inject

class SubscribeCryptoListUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    operator fun invoke(coinsList: List<String>) = cryptoRepository.subscribeCryptoList(coinsList)
}