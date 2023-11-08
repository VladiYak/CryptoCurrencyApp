package com.vladiyak.cryptocurrencyapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpCryptoCompare

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpCoinGecko