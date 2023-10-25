package com.vladiyak.cryptocurrencyapp.fragments.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.model.FavouriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val coinRepository: CoinRepository
): ViewModel() {
    init {
        getAllFavouriteCoin()
    }

    val allFavouriteCoin: MutableLiveData<List<FavouriteEntity>> =
        MutableLiveData<List<FavouriteEntity>>()


    fun addToFavourites(favouriteEntity: FavouriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.addFavourite(favouriteEntity)
        }
    }

    fun getAllFavouriteCoin() {
        viewModelScope.launch(Dispatchers.IO) {
            allFavouriteCoin.postValue(coinRepository.getAllFavourite())
        }
    }

    fun removeCoinFromFavourite(entity: FavouriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.delFavourite(entity)
        }
    }
}