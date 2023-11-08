package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin

interface OnClickListenerTrendingItem {
    fun onItemClick(coin: TrendingCoin)

}