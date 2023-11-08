package com.vladiyak.cryptocurrencyapp.domain.models



data class CoinMarketChart(
    val prices: List<List<Double>>,
    val marketCaps: List<List<Double>>,
    val totalVolumes: List<List<Double>>
)
