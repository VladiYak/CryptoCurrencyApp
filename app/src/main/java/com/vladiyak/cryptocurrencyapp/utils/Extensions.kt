package com.vladiyak.cryptocurrencyapp.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import java.math.RoundingMode

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun String.addPrefix(value: String) = this + value

fun String.addSuffix(value: String) = value + this

fun <T : Parcelable?> Bundle?.getParcelableArg(name: String, clazz: Class<T>): T?
{
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this!!.getParcelable(name, clazz)
    else
        this!!.getParcelable(name) as T?
}