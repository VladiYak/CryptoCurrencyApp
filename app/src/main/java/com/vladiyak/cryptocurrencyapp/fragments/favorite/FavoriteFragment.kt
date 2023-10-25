package com.vladiyak.cryptocurrencyapp.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.databinding.FragmentFavoriteBinding
import com.vladiyak.cryptocurrencyapp.fragments.favorite.adapters.FavouriteHomeAdapter
import com.vladiyak.cryptocurrencyapp.model.FavouriteEntity
import com.vladiyak.cryptocurrencyapp.utils.OnClickListenerFavouriteItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoritesAdapter: FavouriteHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        setRecyclerView()

        //Setting the toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarFav)
        (activity as AppCompatActivity).supportActionBar!!.title = "Favourite Coins";

        // Setting the Listeners
        binding.moveToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }

        return binding.root
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
            favoritesAdapter = FavouriteHomeAdapter(onClickListener = object :
                OnClickListenerFavouriteItem {
                override fun onItemClick(favCoin: FavouriteEntity) {
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
}