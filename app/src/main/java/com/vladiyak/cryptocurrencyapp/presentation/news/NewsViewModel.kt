package com.vladiyak.cryptocurrencyapp.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladiyak.cryptocurrencyapp.data.repositories.CryptoCompareRepositoryImpl
import com.vladiyak.cryptocurrencyapp.domain.model.NewsData
import com.vladiyak.cryptocurrencyapp.domain.repository.CryptoCompareRepository
import com.vladiyak.cryptocurrencyapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: CryptoCompareRepository
): ViewModel() {

    val news: MutableLiveData<ApiResponse<List<NewsData>>> = MutableLiveData()

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        news.postValue(ApiResponse.Loading())

        val response = repository.getLatestNews()
        if (response != null) {
            news.postValue(ApiResponse.Success(response))
        }else {
            news.postValue(ApiResponse.Error("Could not retrieve news, try again!"))
        }
    }
}