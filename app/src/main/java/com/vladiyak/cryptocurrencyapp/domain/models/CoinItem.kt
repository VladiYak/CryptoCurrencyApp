package com.vladiyak.cryptocurrencyapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinItem(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String? = null,
    val currentPrice: Double = 0.0,
    val marketCap: Double = 0.0,
    val marketCapRank: Long = 0,
    val fullyDilutedValuation: Double?,
    val totalVolume: Double = 0.0,
    val high24h: Double = 0.0,
    val low24h: Double = 0.0,
    val priceChange24h: Double = 0.0,
    val priceChangePercentage24h: Double = 0.0,
    val priceChangePercentage7dInCurrency: Double = 0.0,
    val marketCapChange24h: Double = 0.0,
    val marketCapChangePercentage24h: Double = 0.0,
    val circulatingSupply: Double = 0.0,
    val totalSupply: Double? = null,
    val maxSupply: Double? = null,
    val ath: Double = 0.0,
    val athChangePercentage: Double = 0.0,
    val athDate: String? = null,
    val atl: Double = 0.0,
    val atlChangePercentage: Double = 0.0,
    val atlDate: String? = null,
    val lastUpdated: String? = null,
    val priceChangePercentage1hInCurrency: Double = 0.0,
    val sparklineIn7d: SparklineIn7d? = null

) : Parcelable

@Parcelize
data class SparklineIn7d(
    val price: List<Double>? = null
) : Parcelable

