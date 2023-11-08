package com.vladiyak.cryptocurrencyapp.presentation.details

import com.vladiyak.cryptocurrencyapp.domain.models.CoinDetail
import com.vladiyak.cryptocurrencyapp.utils.CoinChartTimeSpan

data class DetailState(
    val priceList: List<List<Double>> = emptyList(),
    val timeRange: CoinChartTimeSpan = CoinChartTimeSpan.TIMESPAN_7DAYS,
    val coinDetail: CoinDetail? = null,
    val isLoading: Boolean = false,
    val message: String = ""
)
