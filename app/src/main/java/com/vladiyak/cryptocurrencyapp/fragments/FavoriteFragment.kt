package com.vladiyak.cryptocurrencyapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.adapters.MarketAdapter
import com.vladiyak.cryptocurrencyapp.api.Api
import com.vladiyak.cryptocurrencyapp.api.ApiInterface
import com.vladiyak.cryptocurrencyapp.databinding.FragmentFavoriteBinding
import com.vladiyak.cryptocurrencyapp.model.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteFragment : Fragment() {


    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var savedList: ArrayList<String>
    private lateinit var savedListItem: ArrayList<CryptoCurrency>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        readData()
        lifecycleScope.launch(Dispatchers.IO){
            val res = Api.getInstance().create(ApiInterface::class.java).getMarketData()

            if (res.body()!= null){
                withContext(Dispatchers.Main){
                    savedListItem = ArrayList()
                    savedListItem.clear()

                    for (savedData in savedList){
                        for (item in res.body()!!.data.cryptoCurrencyList){
                            if (savedData == item.symbol){
                                savedListItem.add(item)
                            }
                        }
                    }
                    binding.spinKitView.visibility = View.GONE
                    binding.watchlistRecyclerView.adapter = MarketAdapter(requireContext(), savedListItem, "favoriteFragment")
                }
            }
        }

        return binding.root
    }
    private fun readData() {
        val sharedPreferences =
            requireContext().getSharedPreferences("savedList", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("savedList", ArrayList<String>().toString())

        val type = object : TypeToken<ArrayList<String>>() {}.type

        savedList = gson.fromJson(json, type)
    }
}