package com.vladiyak.cryptocurrencyapp.domain.model

enum class CoinChartTimeSpan(val value: String) {
    TIMESPAN_1DAYS("1"),
    TIMESPAN_7DAYS("7"),
    TIMESPAN_14DAYS("14"),
    TIMESPAN_30DAYS("30"),
    TIMESPAN_365DAYS("365"),
    TIMESPAN_MAXIMUM("max"),
}