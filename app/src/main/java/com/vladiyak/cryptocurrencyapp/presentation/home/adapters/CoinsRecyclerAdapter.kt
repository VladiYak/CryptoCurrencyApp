package com.vladiyak.cryptocurrencyapp.presentation.home.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vladiyak.cryptocurrencyapp.domain.models.CoinItem
import com.vladiyak.cryptocurrencyapp.utils.OnClickListener

class CoinsRecyclerAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<CoinItem, CoinsViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        return CoinsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, onClickListener)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<CoinItem>() {
        override fun areItemsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem.id == newItem.id
        }

    }
}