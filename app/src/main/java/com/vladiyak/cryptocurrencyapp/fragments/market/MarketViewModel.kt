package com.vladiyak.cryptocurrencyapp.fragments.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MarketListUiState())
    val state: StateFlow<MarketListUiState>
        get() = _state

    init {
        getMarketList()
    }

    private fun getMarketList() {
        coinRepository.getMarketList().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(list = result.data ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(list = emptyList(), isLoading = true)
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(message = result.message ?: "Error!") }
                }
            }
        }.launchIn(viewModelScope)
    }
}