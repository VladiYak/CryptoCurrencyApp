package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem


interface OnClickListener {
    fun onItemClick(coin: CoinItem)
}