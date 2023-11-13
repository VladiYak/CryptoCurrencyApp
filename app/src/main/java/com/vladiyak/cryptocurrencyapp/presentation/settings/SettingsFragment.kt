package com.vladiyak.cryptocurrencyapp.presentation.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.databinding.FragmentHomeBinding
import com.vladiyak.cryptocurrencyapp.databinding.FragmentSettingsBinding
import com.vladiyak.cryptocurrencyapp.presentation.home.HomeViewModel
import com.vladiyak.cryptocurrencyapp.utils.ThemeHelper
import com.vladiyak.cryptocurrencyapp.utils.ThemeMode
import com.vladiyak.cryptocurrencyapp.utils.doOnChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding ?: throw RuntimeException("FragmentSettingsBinding == null")

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        observeViewModel()

        viewModel.isDarkMode.observe(viewLifecycleOwner, Observer {
            binding.settingsDarkModeSwitch.isChecked = it
            binding.settingsDarkModeSwitch.setOnCheckedChangeListener { switch, checked ->
                viewModel.onThemeChanged(checked)
            }
        })
    }

    private fun observeViewModel() {
        viewModel.isDarkMode.doOnChange(this) {
            ThemeHelper.applyTheme(if (it) ThemeMode.Dark else ThemeMode.Light)
        }
    }

}