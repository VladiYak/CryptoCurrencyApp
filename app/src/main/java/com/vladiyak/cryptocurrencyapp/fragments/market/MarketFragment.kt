package com.vladiyak.cryptocurrencyapp.fragments.market

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladiyak.cryptocurrencyapp.R
import activities.MainActivity
import com.vladiyak.cryptocurrencyapp.databinding.FragmentMarketBinding
import com.vladiyak.cryptocurrencyapp.fragments.market.adapters.MarketRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarketFragment : Fragment() {

    private var _binding: FragmentMarketBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: MarketRecyclerAdapter
    private val viewModel: MarketViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.hide()
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun setupRecyclerView() {
        val rvMarket = binding.rvMarket
        adapter = MarketRecyclerAdapter()
        rvMarket.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvMarket.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(rvMarket.context, DividerItemDecoration.VERTICAL)
        rvMarket.addItemDecoration(dividerItemDecoration)
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapter.submitList(state.list)

                    state.isLoading.let {
                        if (it) binding.progressBarMarket.visibility = View.VISIBLE
                        else binding.progressBarMarket.visibility = View.GONE
                    }
                    if (state.message.isNotEmpty()) {
                        Toast.makeText(
                            requireContext(),
                            state.message, Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBarMarket.visibility = View.GONE
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