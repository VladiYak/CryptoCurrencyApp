package com.vladiyak.cryptocurrencyapp.presentation.home.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vladiyak.cryptocurrencyapp.domain.models.TrendingCoin
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerTrendingItem


class TrendingCoinsAdapter(
    private val onClickListener: OnClickListenerTrendingItem
) :
    ListAdapter<TrendingCoin, TrendingViewHolder>(DiffCallBackTrending) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListener)
    }

    companion object DiffCallBackTrending : DiffUtil.ItemCallback<TrendingCoin>() {
        override fun areItemsTheSame(
            oldItem: TrendingCoin,
            newItem: TrendingCoin
        ): Boolean {
            return oldItem.item == newItem.item
        }

        override fun areContentsTheSame(
            oldItem: TrendingCoin,
            newItem: TrendingCoin
        ): Boolean {
            return oldItem.item == newItem.item
        }
    }
}
