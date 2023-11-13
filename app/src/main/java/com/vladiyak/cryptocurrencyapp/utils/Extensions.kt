package com.vladiyak.cryptocurrencyapp.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.io.Serializable
import java.math.RoundingMode

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun String.addPrefix(value: String) = this + value

fun String.addSuffix(value: String) = value + this

fun <T> LiveData<T>.doOnChange(owner: LifecycleOwner, f: (T) -> Unit) {
    observe(owner, Observer {
        f(it)
    })
}
