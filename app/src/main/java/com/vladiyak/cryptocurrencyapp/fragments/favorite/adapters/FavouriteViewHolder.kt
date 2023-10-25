package com.vladiyak.cryptocurrencyapp.fragments.favorite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.databinding.CryptoShowRecylceElementsBinding
import com.vladiyak.cryptocurrencyapp.model.FavouriteEntity
import com.vladiyak.cryptocurrencyapp.utils.DataFormat
import com.vladiyak.cryptocurrencyapp.utils.OnClickListener
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerFavouriteItem

class FavouriteViewHolder(
    val binding: CryptoShowRecylceElementsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FavouriteEntity, onClickListener: OnClickListenerFavouriteItem) {
        binding.apply {
            favLayout.setOnClickListener {
                onClickListener.onItemClick(item)
            }
            binding.apply {
//                Glide.with(binding.root.context)
//                    .load(item.coin_Image_Link)
//                    .placeholder(R.drawable.ic_icons8_loading)
//                    .into(CoinImage)
                CoinImage.load(item.coin_Image_Link) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }

                //Setting Name of Coin
                NameOfCoin.text = DataFormat.formatName(item.coinName!!)

                //Setting Price of Coin
                PriceOfCoin.text = DataFormat.formatPrice(item.price!!)

                // Getting Formatted Data  of Change in 24 Hours
                DataFormat.getChangeFormatted(
                    item.coin_Change_In_24H.toString(),
                    changeIn24hours
                )
            }
        }
    }


    companion object {
        fun from(parent: ViewGroup): FavouriteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CryptoShowRecylceElementsBinding.inflate(layoutInflater, parent, false)
            return FavouriteViewHolder(binding)
        }
    }
}