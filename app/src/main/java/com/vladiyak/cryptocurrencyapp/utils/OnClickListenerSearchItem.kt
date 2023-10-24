package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.search.CoinSearchResponse

interface OnClickListenerSearchItem {

    fun onItemClick(coin: CoinSearchResponse)
}