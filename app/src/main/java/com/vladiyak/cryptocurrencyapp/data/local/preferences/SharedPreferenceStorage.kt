package com.vladiyak.cryptocurrencyapp.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {
    companion object {
        const val PREFS_NAME = "com.vladiyak.cryptocurrencyapp"
        const val PREFS_IS_DARK_MODE = "prefs_is_dark_mode"
    }

    //Create shared preference object on first use
    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override var isDarkMode by BooleanPreference(prefs, PREFS_IS_DARK_MODE, false)
}

