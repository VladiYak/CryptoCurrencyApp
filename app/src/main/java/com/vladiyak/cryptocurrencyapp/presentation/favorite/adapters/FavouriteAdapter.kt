package com.vladiyak.cryptocurrencyapp.presentation.favorite.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerFavouriteItem

class FavouriteAdapter(
    private val onClickListener: OnClickListenerFavouriteItem
) : ListAdapter<FavoriteCoin, FavouriteViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, onClickListener)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<FavoriteCoin>() {
        override fun areItemsTheSame(oldItem: FavoriteCoin, newItem: FavoriteCoin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteCoin, newItem: FavoriteCoin): Boolean {
            return oldItem.coinId == newItem.coinId
        }

    }
}