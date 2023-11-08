package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.domain.model.FavouriteEntity

interface OnClickListenerFavouriteItem {
    fun onItemClick(favCoin: FavouriteEntity)

}