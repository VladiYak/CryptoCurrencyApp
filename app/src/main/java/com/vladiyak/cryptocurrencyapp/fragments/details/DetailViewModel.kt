package com.vladiyak.cryptocurrencyapp.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.domain.repository.CoinRepository
import com.vladiyak.cryptocurrencyapp.model.CoinChartTimeSpan
import com.vladiyak.cryptocurrencyapp.model.FavouriteEntity
import com.vladiyak.cryptocurrencyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState>
        get() = _state

    private var getMarketChartJob: Job? = null

    fun getAllData(id: String) {
        getMarketChartJob?.cancel()
        getMarketChartJob =
            repository.getMarketChart(id, state.value.timeRange.value).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update { it.copy(priceList = result.data?.prices ?: emptyList()) }
                    }
                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                }
            }.launchIn(viewModelScope)
    }

    fun getCoinDetail(id: String){
        repository.getCoinDetail(id).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.update {
                        it.copy(coinDetail = result.data)
                    }
                }
                is Resource.Loading -> {}
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }
    fun setCoinChartTimeSpan(time: Int, id: String) {
        when (time) {
            1 -> _state.update { it.copy(timeRange = CoinChartTimeSpan.TIMESPAN_1DAYS) }
            7 -> _state.update { it.copy(timeRange = CoinChartTimeSpan.TIMESPAN_7DAYS) }
            14 -> _state.update { it.copy(timeRange = CoinChartTimeSpan.TIMESPAN_14DAYS) }
            30 -> _state.update { it.copy(timeRange = CoinChartTimeSpan.TIMESPAN_30DAYS) }
            60 -> _state.update { it.copy(timeRange = CoinChartTimeSpan.TIMESPAN_60DAYS) }
        }
        getAllData(id)
    }

    override fun onCleared() {
        super.onCleared()
        getMarketChartJob = null
    }
}
