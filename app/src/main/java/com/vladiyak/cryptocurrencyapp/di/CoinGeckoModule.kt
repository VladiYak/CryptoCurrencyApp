package com.vladiyak.cryptocurrencyapp.di

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.CoinGeckoApiService
import com.vladiyak.cryptocurrencyapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoModule {

    @Provides
    @Singleton
    fun provideApiService(@OkHttpCoinGecko okHttpClient: OkHttpClient): CoinGeckoApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_COIN_GECKO)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    @OkHttpCoinGecko
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}