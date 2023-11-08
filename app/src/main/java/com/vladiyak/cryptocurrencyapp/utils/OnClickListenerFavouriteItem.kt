package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin

interface OnClickListenerFavouriteItem {
    fun onItemClick(favCoin: FavoriteCoin)

}