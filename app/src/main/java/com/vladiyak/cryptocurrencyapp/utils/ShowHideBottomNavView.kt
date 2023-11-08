package com.vladiyak.cryptocurrencyapp.utils

import com.vladiyak.cryptocurrencyapp.MainActivity
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vladiyak.cryptocurrencyapp.R

fun Fragment.hideBottomNavigationView() {
    val bottomNavigationView = (activity as MainActivity)
        .findViewById<BottomNavigationView>(R.id.bottomAppBar)
    bottomNavigationView.visibility = View.GONE
}

fun Fragment.showBottomNavigationView() {
    val bottomNavigationView = (activity as MainActivity)
        .findViewById<BottomNavigationView>(R.id.bottomAppBar)
    bottomNavigationView.visibility = View.VISIBLE
}