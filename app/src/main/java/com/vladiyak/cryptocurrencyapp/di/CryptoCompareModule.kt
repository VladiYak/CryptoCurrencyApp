package com.vladiyak.cryptocurrencyapp.di

import com.vladiyak.cryptocurrencyapp.data.network.newsapi.CryptoCompareApiService
import com.vladiyak.cryptocurrencyapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CryptoCompareModule {

    @Provides
    @Singleton
    fun provideApiService(@OkHttpCryptoCompare okHttpClient: OkHttpClient): CryptoCompareApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_CRYPTO_COMPARE)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    @OkHttpCryptoCompare
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { apiKeyAsQuery(it) }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("api_key", Constants.ApiKeyCryptoCompare).build())
            .build()
    )
}