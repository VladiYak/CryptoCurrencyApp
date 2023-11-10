package com.vladiyak.cryptocurrencyapp.presentation.favorite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.databinding.FavoriteRvItemBinding
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerFavouriteItem
import com.vladiyak.cryptocurrencyapp.utils.addPrefix
import com.vladiyak.cryptocurrencyapp.utils.addSuffix
import java.util.Locale

class FavouriteViewHolder(
    val binding: FavoriteRvItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FavoriteCoin, onClickListener: OnClickListenerFavouriteItem) {
        binding.apply {
            itemLayoutId.setOnClickListener {
                onClickListener.onItemClick(item)
            }
            binding.apply {
//                Glide.with(binding.root.context)
//                    .load(item.coin_Image_Link)
//                    .placeholder(R.drawable.ic_icons8_loading)
//                    .into(CoinImage)
                coinImage.load(item.coinImage) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }

                tvCoinName.text = item.coinName

                tvCoinSymbol.text = item.symbol.uppercase(Locale.ROOT)

                tvCoinPrice.text = item.price.toString().addPrefix("$")

                if ((item.priceChangePercentage7d ?: 0.0) > 0) {
                    tvPercentage.text = item.priceChangePercentage7d?.toString()?.addPrefix("%")?.addSuffix("+")
                    binding.tvPercentage.setBackgroundResource(
                        R.drawable.background_corners_percent_increase
                    )
                } else {
                    tvPercentage.text = item.priceChangePercentage7d?.toString()?.addPrefix("%")
                    binding.tvPercentage.setBackgroundResource(
                        R.drawable.background_corners_percent_decrease
                    )
                }
            }
        }
    }


    companion object {
        fun from(parent: ViewGroup): FavouriteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FavoriteRvItemBinding.inflate(layoutInflater, parent, false)
            return FavouriteViewHolder(binding)
        }
    }
}