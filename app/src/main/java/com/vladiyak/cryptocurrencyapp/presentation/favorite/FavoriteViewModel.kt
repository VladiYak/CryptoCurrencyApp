package com.vladiyak.cryptocurrencyapp.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val coinRepository: CoinRepository
): ViewModel() {

    private var _allFavouriteCoin: MutableLiveData<List<FavoriteCoin>> =
        MutableLiveData<List<FavoriteCoin>>()
    val allFavouriteCoin: LiveData<List<FavoriteCoin>> = _allFavouriteCoin


    fun addToFavourites(favoriteCoin: FavoriteCoin) {
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.addFavourite(favoriteCoin)
        }
    }

    fun getAllFavouriteCoin() {
        viewModelScope.launch(Dispatchers.IO) {
            _allFavouriteCoin.postValue(coinRepository.getAllFavourite())
        }
    }

    fun removeCoinFromFavourite(favoriteCoin: FavoriteCoin) {
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.deleteFavourite(favoriteCoin)
        }
    }
    init {
        getAllFavouriteCoin()
    }
}