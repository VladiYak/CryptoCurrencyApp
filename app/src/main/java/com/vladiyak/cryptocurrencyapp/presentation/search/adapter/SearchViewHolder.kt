package com.vladiyak.cryptocurrencyapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.CoinSearchResponseDto
import com.vladiyak.cryptocurrencyapp.databinding.SearchItemBinding
import com.vladiyak.cryptocurrencyapp.domain.models.CoinSearchResponse
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerSearchItem

class SearchViewHolder(val binding: SearchItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CoinSearchResponse, onClickListener: OnClickListenerSearchItem) {
        binding.apply {
            searchLayout.setOnClickListener {
                onClickListener.onItemClick(item)
            }

            binding.apply {
                with(binding) {
                    tvMarketRank.text = item.marketCapRank.toString()
                    tvSymbol.text = item.symbol
                    ivIcon.load(item.imageUrl) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                    tvName.text = item.name
                }
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): SearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SearchItemBinding.inflate(layoutInflater, parent, false)
            return SearchViewHolder(binding)
        }
    }
}