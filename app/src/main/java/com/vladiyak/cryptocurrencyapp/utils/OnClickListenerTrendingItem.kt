package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.TrendingCoin
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.TrendingCoinItem
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.search.CoinSearchResponse

interface OnClickListenerTrendingItem {
    fun onItemClick(coin: TrendingCoin)

}