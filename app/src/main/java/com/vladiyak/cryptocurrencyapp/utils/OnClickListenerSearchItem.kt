package com.vladiyak.cryptocurrencyapp.utils


import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.CoinSearchResponse

interface OnClickListenerSearchItem {

    fun onItemClick(coin: CoinSearchResponse)
}