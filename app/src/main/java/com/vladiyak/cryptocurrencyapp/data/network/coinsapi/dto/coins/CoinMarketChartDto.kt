package com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins

import com.google.gson.annotations.SerializedName
import com.vladiyak.cryptocurrencyapp.domain.models.CoinMarketChart

data class CoinMarketChartDto(
    val prices: List<List<Double>>,

    @SerializedName("market_caps")
    val marketCaps: List<List<Double>>,

    @SerializedName("total_volumes")
    val totalVolumes: List<List<Double>>
)

fun CoinMarketChartDto.toCoinMarketChart(): CoinMarketChart {
    return CoinMarketChart(
        prices = prices,
        marketCaps = marketCaps,
        totalVolumes = totalVolumes
    )
}
