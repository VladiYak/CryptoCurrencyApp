package com.vladiyak.cryptocurrencyapp.domain.models



data class FavoriteCoin(
    val coinId: String,
    val coinName: String?,
    val symbol: String,
    val price: String?,
    val coinImage: String?,
    val priceChangePercentage7d: Double?,
)

