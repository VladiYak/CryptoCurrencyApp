package com.vladiyak.cryptocurrencyapp.api.client

import com.vladiyak.cryptocurrencyapp.api.CryptoCompareAPI
import com.vladiyak.cryptocurrencyapp.utils.Constants.ApiKey
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object CryptoCompareClient {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/v2/"

    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("api_key", ApiKey).build())
            .build()
    )

    private fun apiKeyAsHeader(it: Interceptor.Chain) = it.proceed(
        it.request()
            .newBuilder()
            .addHeader("api_key", ApiKey)
            .build()
    )

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { apiKeyAsQuery(it) }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val api by lazy {
        retrofit.create(CryptoCompareAPI::class.java)
    }
}