package com.vladiyak.cryptocurrencyapp.domain.models

import com.google.gson.annotations.SerializedName

data class TrendingCoinItem(
    val id: String,
    val coinId: Int,
    val name: String,
    val symbol: String,
    val marketCapRank: Int,
    val thumb: String,
    val small: String,
    val large: String,
    val slug: String,
    val priceBtc: Double,
    val score: Int
)
