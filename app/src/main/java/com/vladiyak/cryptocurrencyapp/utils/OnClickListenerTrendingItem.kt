package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.coins.TrendingCoin

interface OnClickListenerTrendingItem {
    fun onItemClick(coin: TrendingCoin)

}