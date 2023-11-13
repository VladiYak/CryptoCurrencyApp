package com.vladiyak.cryptocurrencyapp.di


import com.vladiyak.cryptocurrencyapp.data.repositories.CoinRepositoryImpl
import com.vladiyak.cryptocurrencyapp.data.repositories.CryptoCompareRepositoryImpl
import com.vladiyak.cryptocurrencyapp.data.repositories.SettingsRepositoryImpl
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import com.vladiyak.cryptocurrencyapp.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository

    @Binds
    @Singleton
    abstract fun bindCryptoCompareRepository(cryptoCompareRepositoryImpl: CryptoCompareRepositoryImpl): CryptoCompareRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

}