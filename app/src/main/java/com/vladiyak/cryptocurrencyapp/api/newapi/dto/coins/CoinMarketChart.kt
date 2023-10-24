package com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins

import com.google.gson.annotations.SerializedName

data class CoinMarketChart(
    val prices: List<List<Double>>,

    @SerializedName("market_caps")
    val marketCaps: List<List<Double>>,

    @SerializedName("total_volumes")
    val totalVolumes: List<List<Double>>
)
