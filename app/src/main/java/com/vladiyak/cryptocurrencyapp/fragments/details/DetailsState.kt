package com.vladiyak.cryptocurrencyapp.fragments.details

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinDetail
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.MarketData
import com.vladiyak.cryptocurrencyapp.model.CoinChartTimeSpan

data class DetailState(
    val priceList: List<List<Double>> = emptyList(),
    val timeRange: CoinChartTimeSpan = CoinChartTimeSpan.TIMESPAN_7DAYS,
    val coinDetail: CoinDetail? = null,
    val isLoading: Boolean = false,
    val message: String = ""
)
