package com.vladiyak.cryptocurrencyapp.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vladiyak.cryptocurrencyapp.fragments.TopGainLoseFragment

class TopGainLoseAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TopGainLoseFragment()
        val bundle = Bundle()

        bundle.putInt("position",position)
        fragment.arguments = bundle
        return fragment
    }

}