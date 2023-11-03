package com.vladiyak.cryptocurrencyapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vladiyak.cryptocurrencyapp.activities.MainActivity
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinItem
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.TrendingCoin
import com.vladiyak.cryptocurrencyapp.databinding.FragmentHomeBinding
import com.vladiyak.cryptocurrencyapp.fragments.home.adapters.CoinsRecyclerAdapter
import com.vladiyak.cryptocurrencyapp.fragments.home.adapters.TrendingCoinsAdapter
import com.vladiyak.cryptocurrencyapp.utils.OnClickListener
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerTrendingItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapterCoins: CoinsRecyclerAdapter
    private lateinit var adapterTrending: TrendingCoinsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.hide()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        observeData()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCoins()
            viewModel.getTrendingCoins()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerViews() {
        adapterCoins = CoinsRecyclerAdapter(onClickListener = object : OnClickListener {
            override fun onItemClick(coin: CoinItem) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    coin.id
                )
                findNavController().navigate(action)
            }
        })

        adapterTrending =
            TrendingCoinsAdapter(onClickListener = object : OnClickListenerTrendingItem {
                override fun onItemClick(coin: TrendingCoin) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        coin.item.id
                    )
                    findNavController().navigate(action)
                }

            })
        binding.rvCoinList.adapter = adapterCoins
        val rvTrending = binding.rvTrendingCoins
        rvTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvTrending.adapter = adapterTrending
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect { state ->
                        adapterCoins.submitList(state.coinList)
                        adapterTrending.submitList(state.trendingCoinList)

                        state.isLoading.let {
                            if (it) {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.nestedScrollView.visibility = View.GONE
                            } else {
                                binding.progressBar.visibility = View.GONE
                                binding.nestedScrollView.visibility = View.VISIBLE
                            }
                        }
                        if (state.message.isNotEmpty()) {
                            Snackbar.make(
                                requireContext(),
                                binding.layoutId,
                                state.message,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}