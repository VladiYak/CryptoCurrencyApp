package com.vladiyak.cryptocurrencyapp.di

import com.vladiyak.cryptocurrencyapp.data.api.CryptoCompareAPI
import com.vladiyak.cryptocurrencyapp.data.api.newapi.ApiService
import com.vladiyak.cryptocurrencyapp.utils.Constants
import com.vladiyak.cryptocurrencyapp.utils.ConstantsNew
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CryptoCompareModule {

    @Provides
    @Singleton
    fun provideApiService(@OkHttpCryptoCompare okHttpClient: OkHttpClient): CryptoCompareAPI {
        return Retrofit.Builder()
            .baseUrl(ConstantsNew.BASE_URL_CRYPTO_COMPARE)
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
            .url(chain.request().url.newBuilder().addQueryParameter("api_key", Constants.ApiKey).build())
            .build()
    )
}