package com.vladiyak.cryptocurrencyapp.utils


import com.vladiyak.cryptocurrencyapp.domain.models.CoinSearchResponse

interface OnClickListenerSearchItem {

    fun onItemClick(coin: CoinSearchResponse)
}