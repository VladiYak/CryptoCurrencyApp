package com.vladiyak.cryptocurrencyapp.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.databinding.FragmentFavoriteBinding
import com.vladiyak.cryptocurrencyapp.domain.models.FavoriteCoin
import com.vladiyak.cryptocurrencyapp.presentation.favorite.adapters.FavouriteAdapter
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerFavouriteItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding == null")

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoritesAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        //Setting the toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarFav)
        (activity as AppCompatActivity).supportActionBar!!.title = "Favourite Coins";

        // Setting the Listeners
        binding.moveToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }

    }

    private fun setRecyclerView() {
        viewModel.getAllFavouriteCoin()
        binding.favouriteRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.allFavouriteCoin.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.nothingUi.visibility = View.VISIBLE
            } else {
                binding.nothingUi.visibility = View.INVISIBLE
            }
            favoritesAdapter = FavouriteAdapter(onClickListener = object :
                OnClickListenerFavouriteItem {
                override fun onItemClick(favCoin: FavoriteCoin) {
                    val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(
                        favCoin.coinId
                    )
                    findNavController().navigate(action)
                }
            })
            binding.favouriteRecyclerView.adapter = favoritesAdapter
            favoritesAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}