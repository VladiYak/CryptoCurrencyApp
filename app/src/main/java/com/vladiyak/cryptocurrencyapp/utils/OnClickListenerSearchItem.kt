package com.vladiyak.cryptocurrencyapp.utils


import com.vladiyak.cryptocurrencyapp.data.api.newapi.dto.search.CoinSearchResponse

interface OnClickListenerSearchItem {

    fun onItemClick(coin: CoinSearchResponse)
}