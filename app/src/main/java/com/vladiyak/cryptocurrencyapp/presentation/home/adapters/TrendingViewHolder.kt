package com.vladiyak.cryptocurrencyapp.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.vladiyak.cryptocurrencyapp.data.api.newapi.dto.coins.TrendingCoin
import com.vladiyak.cryptocurrencyapp.databinding.TrendingCoinsItemBinding
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerTrendingItem
import com.vladiyak.cryptocurrencyapp.utils.format


class TrendingViewHolder(
    private val binding: TrendingCoinsItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(coin: TrendingCoin, onClickListener: OnClickListenerTrendingItem) {
        binding.apply {
            materialCard.setOnClickListener {
                onClickListener.onItemClick(coin)
            }

            binding.apply {
                ivTrendIcon.load(coin.item.small) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }

                tvTrendName.text = coin.item.name
                tvCoinSymbol.text = coin.item.symbol
                tvCoinRank.text = coin.item.marketCapRank.toString()
            }
        }
    }


    companion object {
        fun from(parent: ViewGroup): TrendingViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TrendingCoinsItemBinding.inflate(layoutInflater, parent, false)
            return TrendingViewHolder(binding)
        }
    }
}
