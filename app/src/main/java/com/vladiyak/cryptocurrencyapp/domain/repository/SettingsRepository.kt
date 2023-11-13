package com.vladiyak.cryptocurrencyapp.domain.repository

interface SettingsRepository {

    fun isDarkModeEnabled(): Boolean

    fun setThemeMode(isDarkMode: Boolean)
}