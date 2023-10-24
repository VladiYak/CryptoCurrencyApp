package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.TrendingCoin
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.search.CoinSearchResponse

interface OnClickListener {
    fun onItemClick(coin: CoinItem)
}