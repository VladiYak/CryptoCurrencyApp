package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.TrendingCoin
import com.vladiyak.cryptocurrencyapp.model.FavouriteEntity

interface OnClickListenerFavouriteItem {
    fun onItemClick(favCoin: FavouriteEntity)

}