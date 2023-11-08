package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.data.api.newapi.dto.coins.CoinItem

interface OnClickListener {
    fun onItemClick(coin: CoinItem)
}