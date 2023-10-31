package com.vladiyak.cryptocurrencyapp.utils

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vladiyak.cryptocurrencyapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@BindingAdapter("android:loadImage")
fun loadCoinIcon(imageView: ImageView, url: String?) {
    imageView.load(url) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("android:myText")
fun setText(textView: TextView, price: Double) {
    textView.text = price.toString().addSuffix("$")
}

@BindingAdapter("android:TextForSupply")
fun setTextSupply(textView: TextView, price: Double) {
    textView.text = String.format("%.2f", price)
}

@BindingAdapter("android:TextForVolume")
fun setTextVolume(textView: TextView, price: Double) {
    textView.text = String.format("%.0f", price)
}



@BindingAdapter("android:TextDate")
fun setText(textView: TextView, orderDate: String?) {
    val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val yourDesiredFormat = SimpleDateFormat("dd-MMM-YYYY")
    try {
        val date = orderDate?.let { zonedFormat.parse(it) }
        textView.text = date?.let { yourDesiredFormat.format(it) }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
}


@BindingAdapter("android:TextForMarketCap")
fun setTextMarketCap(textView: TextView, price: Double) {
    textView.text = String.format("%.2f", price).addSuffix("$")
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_android)
                    .error(R.drawable.ic_android)
            )
            .into(imgView)
    }
}