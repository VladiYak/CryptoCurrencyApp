package com.vladiyak.cryptocurrencyapp.presentation.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vladiyak.cryptocurrencyapp.data.network.coinsapi.dto.search.CoinSearchResponseDto
import com.vladiyak.cryptocurrencyapp.domain.models.CoinSearchResponse
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerSearchItem

class SearchRecyclerAdapter(
    private val onClickListener: OnClickListenerSearchItem
) :
    ListAdapter<CoinSearchResponse, SearchViewHolder>(SearchDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListener)
    }

    companion object SearchDiffCallBack : DiffUtil.ItemCallback<CoinSearchResponse>() {
        override fun areItemsTheSame(
            oldItem: CoinSearchResponse,
            newItem: CoinSearchResponse
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CoinSearchResponse,
            newItem: CoinSearchResponse
        ): Boolean = oldItem.id == newItem.id
    }
}
