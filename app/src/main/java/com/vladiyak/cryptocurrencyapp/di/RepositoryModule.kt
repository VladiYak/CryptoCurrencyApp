package com.vladiyak.cryptocurrencyapp.di


import com.vladiyak.cryptocurrencyapp.data.repositories.CoinRepositoryImpl
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
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
}