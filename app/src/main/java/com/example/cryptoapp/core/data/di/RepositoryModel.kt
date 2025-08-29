package com.example.cryptoapp.data.di

import com.example.cryptoapp.data.network.SocketHandler
import com.example.cryptoapp.data.repository.CryptoRepositoryImpl
import com.example.cryptoapp.domain.interfaces.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModel {
    @Provides
    @Singleton
    fun  provideCryptoRepository(socketHandler: SocketHandler) : CryptoRepository = CryptoRepositoryImpl(socketHandler)
}