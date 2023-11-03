package com.vladiyak.cryptocurrencyapp.fragments.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.activities.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinDetail
import com.vladiyak.cryptocurrencyapp.databinding.FragmentDetailBinding
import com.vladiyak.cryptocurrencyapp.fragments.favorite.FavoriteViewModel
import com.vladiyak.cryptocurrencyapp.model.FavouriteEntity
import com.vladiyak.cryptocurrencyapp.utils.CustomMarkerView
import com.vladiyak.cryptocurrencyapp.utils.XAxisValueFormatter
import com.vladiyak.cryptocurrencyapp.utils.addPrefix
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private val viewModelFav: FavoriteViewModel by viewModels()
    private var isIncreasing: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.hide()
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoinDetail(args.coinId)
        viewModel.getAllData(args.coinId)
        observeData()
        checkingAlreadyFavorite()
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        selectTimeSpan()

        binding.favtoggleButton.setOnClickListener {

            // Favourite Listener
            binding.coin?.let {
                favouriteListener(it)
            }
        }
    }


    private fun checkingAlreadyFavorite() {
        lifecycleScope.launch {
            viewModelFav.allFavouriteCoin.observe(viewLifecycleOwner) { favouriteEntities ->
                favouriteEntities.forEach {
                    if (it.coinId == args.coinId) {
                        binding.favtoggleButton.setImageResource(R.drawable.ic_star)
                        binding.favtoggleButton.tag = "ON"
                    }
                }
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    if (state.timeRange.value == "7") {
                        setDefaultPercentageChange()
                    }
                    displayLineChart(state.priceList)
                    binding.coin = state.coinDetail
                    state.isLoading.let {
                        when (it) {
                            true -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.materialCardPriceChange.visibility = View.INVISIBLE
                            }

                            false -> {
                                binding.progressBar.visibility = View.INVISIBLE
                                delay(1000)
                                binding.materialCardPriceChange.visibility = View.VISIBLE
                            }
                        }

                        if (state.message.isNotEmpty()) {
                            Snackbar.make(
                                requireContext(),
                                binding.layout,
                                state.message,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun selectTimeSpan(id: String = args.coinId) {
        with(binding) {
            chip1.setOnClickListener {
                val percent = viewModel.state.value.coinDetail?.marketData?.priceChangePercentage24h
                binding.txtPriceChange.text = String.format("%.2f", percent).addPrefix("%")
                if ((viewModel.state.value.coinDetail?.marketData?.priceChangePercentage24h
                        ?: 0.0) > 0
                ) {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.greenLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_up_24)
                    isIncreasing = true
                } else {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.redLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_down_24)
                    isIncreasing = false
                }
                viewModel.setCoinChartTimeSpan("1", id)
            }
            chip7.setOnClickListener {
                setDefaultPercentageChange()
                viewModel.setCoinChartTimeSpan("7", id)
            }
            chip14.setOnClickListener {
                val percent = viewModel.state.value.coinDetail?.marketData?.priceChangePercentage14d
                binding.txtPriceChange.text = String.format("%.2f", percent).addPrefix("%")
                if ((viewModel.state.value.coinDetail?.marketData?.priceChangePercentage14d
                        ?: 0.0) > 0
                ) {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.greenLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_up_24)
                    isIncreasing = true
                } else {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.redLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_down_24)
                    isIncreasing = false
                }
                viewModel.setCoinChartTimeSpan("14", id)
            }
            chip30.setOnClickListener {
                val percent = viewModel.state.value.coinDetail?.marketData?.priceChangePercentage30d
                binding.txtPriceChange.text = String.format("%.2f", percent).addPrefix("%")
                if ((viewModel.state.value.coinDetail?.marketData?.priceChangePercentage30d
                        ?: 0.0) > 0
                ) {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.greenLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_up_24)
                    isIncreasing = true
                } else {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.redLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_down_24)
                    isIncreasing = false
                }
                viewModel.setCoinChartTimeSpan("30", id)
            }
            chip365.setOnClickListener {
                val percent =
                    viewModel.state.value.coinDetail?.marketData?.priceChangePercentage365d
                binding.txtPriceChange.text = String.format("%.2f", percent).addPrefix("%")
                if ((viewModel.state.value.coinDetail?.marketData?.priceChangePercentage365d
                        ?: 0.0) > 0
                ) {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.greenLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_up_24)
                    isIncreasing = true
                } else {
                    binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.redLine))
                    binding.imageView.setImageResource(R.drawable.ic_arrow_down_24)
                    isIncreasing = false
                }
                viewModel.setCoinChartTimeSpan("365", id)

            }

            chipMax.setOnClickListener {
                viewModel.setCoinChartTimeSpan("max", id)
                lifecycleScope.launch {
                    delay(1000)
                    val priceList = viewModel.state.value.priceList
                    val percent = (if (priceList.isNotEmpty()) {
                        (priceList.last()[1] / priceList.first()[1] - 1) * 100
                    } else {
                        0.0
                    })

                    binding.txtPriceChange.text = String.format("%.2f", percent).addPrefix("%")
                    if (percent > 0) {
                        binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.greenLine))
                        binding.imageView.setImageResource(R.drawable.ic_arrow_up_24)
                        isIncreasing = true
                    } else {
                        binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.redLine))
                        binding.imageView.setImageResource(R.drawable.ic_arrow_down_24)
                        isIncreasing = false
                    }
                }
            }
        }
    }

    private fun setDefaultPercentageChange() {
        val percent = viewModel.state.value.coinDetail?.marketData?.priceChangePercentage7d
        binding.txtPriceChange.text = String.format("%.2f", percent).addPrefix("%")
        if ((viewModel.state.value.coinDetail?.marketData?.priceChangePercentage7d
                ?: 0.0) > 0
        ) {
            binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.greenLine))
            binding.imageView.setImageResource(R.drawable.ic_arrow_up_24)
            isIncreasing = true
        } else {
            binding.materialCardPriceChange.setCardBackgroundColor(resources.getColor(R.color.redLine))
            binding.imageView.setImageResource(R.drawable.ic_arrow_down_24)
            isIncreasing = false
        }
    }

    private fun displayLineChart(chartData: List<List<Double>>) {
        binding.lineChart.apply {
            val values = ArrayList<Entry>()

            for (i in chartData.indices) {
                try {
                    values.add(
                        Entry(
                            chartData[i][0].toFloat(), // timestamp
                            chartData[i][1].toFloat() // price
                        )
                    )
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            val lineDataSet = LineDataSet(values, "Value")
            lineDataSet.setDrawFilled(true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            xAxis.labelRotationAngle = 90f
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(false)
            legend.isEnabled = false
            xAxis.valueFormatter = XAxisValueFormatter()
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            setScaleEnabled(false)

            lineDataSet.setDrawCircles(false)
            lineDataSet.disableDashedLine()
            lineDataSet.setDrawValues(false)
            lineDataSet.setDrawHighlightIndicators(false)



            description.text = "Usd"
            val data = LineData(lineDataSet)
            this.data = data
            lineDataSet.fillDrawable = lineFillDrawable()
            lineDataSet.lineWidth = 2f
            lineDataSet.color = lineDrawable()
            setTouchEnabled(true)
            setPinchZoom(true)
            invalidate()
            animateX(1500, Easing.EaseInExpo)
            val customMarker = CustomMarkerView(context, R.layout.chart_hourly_marker_view)
            marker = customMarker

        }
    }

    private fun lineFillDrawable(): Drawable? {
        return if (isIncreasing) {
            ContextCompat.getDrawable(requireContext(), R.drawable.chart_fade_green)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.chart_fade_red)
        }
    }

    private fun lineDrawable(): Int {
        return if (isIncreasing) {
            ContextCompat.getColor(requireContext(), R.color.greenLine)
        } else {
            ContextCompat.getColor(requireContext(), R.color.redLine)
        }
    }

    private fun favouriteListener(data: CoinDetail) {
        // Creating Favourite Entity
        val element = FavouriteEntity(
            data.id,
            data.name,
            data.symbol,
            data.marketData?.currentPrice?.usd.toString(),
            data.image?.large,
            data.marketData?.priceChangePercentage24h.toString(),
        )

        if (binding.favtoggleButton.tag != "ON") {
            // Changing  the Image To Filled
            binding.favtoggleButton.setImageResource(R.drawable.ic_star)
            //  Adding To DB
            viewModelFav.addToFavourites(element)
            // Changing the TAG to ON
            binding.favtoggleButton.tag = "ON"
        } else {
            // Deleting From Database
            viewModelFav.removeCoinFromFavourite(element)
            //Changing the Image To Border
            binding.favtoggleButton.setImageResource(R.drawable.ic_star_outline)
            // Setting TAG to OFF
            binding.favtoggleButton.tag = "OFF"
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}