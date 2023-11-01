package com.vladiyak.cryptocurrencyapp.fragments.home.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.databinding.CoinListItemBinding
import com.vladiyak.cryptocurrencyapp.utils.OnClickListener
import com.vladiyak.cryptocurrencyapp.utils.addPrefix
import com.vladiyak.cryptocurrencyapp.utils.addSuffix
import java.util.*

class CoinsViewHolder(
    val binding: CoinListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CoinItem, onClickListener: OnClickListener) {
        binding.apply {
            itemLayoutId.setOnClickListener {
                onClickListener.onItemClick(item)
            }

            binding.apply {
                coinImage.load(item.image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }

                tvCoinName.text = item.name
                tvCoinSymbol.text = item.symbol.uppercase(Locale.ROOT)
                tvCoinPrice.text =
                    item.currentPrice.toString().addPrefix("$") //custom string extensions
                tvPercentage.text = item.priceChangePercentage24h.toString().addSuffix("%")
            }
            if (item.priceChangePercentage24h > 0) {
                binding.tvPercentage.setTextColor(
                    Color.parseColor("#50C878")
                )
//                binding.itemLayoutId.setBackgroundResource(R.drawable.coins_item_bg_increase)
            } else {
                binding.tvPercentage.setTextColor(
                    Color.parseColor("#FF5733")
                )
//                binding.itemLayoutId.setBackgroundResource(R.drawable.coins_item_bg_decrease)
            }
        }
    }


    companion object {
        fun from(parent: ViewGroup): CoinsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CoinListItemBinding.inflate(layoutInflater, parent, false)
            return CoinsViewHolder(binding)
        }
    }
}