package com.vladiyak.cryptocurrencyapp.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.databinding.FragmentNewsBinding
import com.vladiyak.cryptocurrencyapp.presentation.news.adapter.INewsRVAdapter
import com.vladiyak.cryptocurrencyapp.presentation.news.adapter.NewsRVAdapter
import com.vladiyak.cryptocurrencyapp.utils.ApiResponse
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsFragment : Fragment(), INewsRVAdapter {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")

    private val newsAdapter = NewsRVAdapter(this)
    private lateinit var viewModel: NewsViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initializing navController
        navController = Navigation.findNavController(view)

        //initializing recyclerView
        setUpRecyclerView()

        //initializing viewModel
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        viewModel.getNews()

        viewModel.news.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is ApiResponse.Success -> {
                    _binding?.shimmerLayoutNews?.stopShimmer()
                    _binding?.shimmerLayoutNews?.visibility = View.GONE
                    _binding?.rvNews?.visibility = View.VISIBLE
                    newsAdapter.submitList(response.data)
                }
                is ApiResponse.Loading -> {
                    _binding?.shimmerLayoutNews?.visibility = View.VISIBLE
                    _binding?.shimmerLayoutNews?.startShimmer()
                }
                is ApiResponse.Error -> {
                    _binding?.shimmerLayoutNews?.stopShimmer()
                    _binding?.shimmerLayoutNews?.visibility = View.GONE
                    Snackbar.make(view, "Could yes retrieve news, restart app!", Snackbar.LENGTH_SHORT).show()
                }
            }
            response.responseMessage
        })
    }

    private fun setUpRecyclerView() {
        _binding?.rvNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onNewsArticleClicked(url: String) {
        val bundle = bundleOf("articleUrl" to url)
        navController.navigate(R.id.action_newsFragment_to_articleOpenFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
