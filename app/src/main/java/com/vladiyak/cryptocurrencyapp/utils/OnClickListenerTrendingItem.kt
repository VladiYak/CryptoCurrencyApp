package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.data.api.newapi.dto.coins.TrendingCoin

interface OnClickListenerTrendingItem {
    fun onItemClick(coin: TrendingCoin)

}