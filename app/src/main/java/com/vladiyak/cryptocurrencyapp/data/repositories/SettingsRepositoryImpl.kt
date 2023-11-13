package com.vladiyak.cryptocurrencyapp.data.repositories

import com.vladiyak.cryptocurrencyapp.data.local.preferences.PreferenceStorage
import com.vladiyak.cryptocurrencyapp.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val preferenceStorage: PreferenceStorage): SettingsRepository {

    override fun isDarkModeEnabled(): Boolean {
        return preferenceStorage.isDarkMode
    }

    override fun setThemeMode(isDarkMode: Boolean) {
        preferenceStorage.isDarkMode = isDarkMode
    }
}