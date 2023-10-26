package com.vladiyak.cryptocurrencyapp.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.vladiyak.cryptocurrencyapp.R
import com.vladiyak.cryptocurrencyapp.activities.MainActivity
import com.vladiyak.cryptocurrencyapp.api.newapi.dto.coins.CoinDetail
import com.vladiyak.cryptocurrencyapp.databinding.FragmentDetailBinding
import com.vladiyak.cryptocurrencyapp.fragments.favorite.FavoriteViewModel
import com.vladiyak.cryptocurrencyapp.model.FavouriteEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private val viewModelFav: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.hide()
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel.getAllData(args.coinId)
        observeData()
        viewModel.getCoinDetail(args.coinId)
        checkingAlreadyFavorite()
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                    val chartData = getData(state.priceList)
                    displayLineChart(chartData)
                    binding.coin = state.coinDetail
                }
            }
        }
    }

    private fun selectTimeSpan(id: String = args.coinId) {
        with(binding) {
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    radioButton1.id -> viewModel.setCoinChartTimeSpan(1, id)
                    radioButton7.id -> viewModel.setCoinChartTimeSpan(7, id)
                    radioButton14.id -> viewModel.setCoinChartTimeSpan(14, id)
                    radioButton30.id -> viewModel.setCoinChartTimeSpan(30, id)
                    radioButton60.id -> viewModel.setCoinChartTimeSpan(60, id)
                }
            }
        }
    }

    private fun displayLineChart(chartData: Pair<List<String>, List<Entry>>) {
        binding.lineChart.apply {
            val lineDataSet = LineDataSet(chartData.second, "Value")
            lineDataSet.setDrawFilled(true)
            val formatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return if (value.toInt() < chartData.first.size) chartData.first[value.toInt()] else ""
                }
            }
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            xAxis.valueFormatter = formatter
            xAxis.labelRotationAngle = 90f

            lineDataSet.setDrawCircles(false)
            lineDataSet.disableDashedLine()
            lineDataSet.setDrawValues(false)
            description.text = "Usd"
            val data = LineData(lineDataSet)
            this.data = data
//            lineDataSet.fillDrawable = lineFillDrawable()
            setTouchEnabled(true)
            setPinchZoom(true)
            invalidate()

            animateX(1500, Easing.EaseInExpo)
        }
    }

//    private fun lineFillDrawable(): Drawable? {
//        return if (args.priceChangePercentage24h > 0) {
//            ContextCompat.getDrawable(requireContext(), R.drawable.chart_bg_increase)
//        } else {
//            ContextCompat.getDrawable(requireContext(), R.drawable.chart_bg_decrease)
//        }
//    }

    private fun favouriteListener(data: CoinDetail) {
        // Creating Favourite Entity
        val element = FavouriteEntity(
            data.id,
            data.name,
            data.marketData?.currentPrice?.usd.toString(),
            data.image?.large,
            data.marketData?.priceChangePercentage24h.toString()
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

    private fun getData(list: List<List<Double>>): Pair<List<String>, List<Entry>> {
        val xAxisValues = arrayListOf<String>()
        val entries = arrayListOf<Entry>()

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)

        list.forEachIndexed { index, entry ->
            val date = Date(entry[0].toLong())
            val label = simpleDateFormat.format(date)
            xAxisValues.add(label)
            entries.add(Entry(index.toFloat(), entry[1].toFloat()))
        }

        return Pair(xAxisValues, entries)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}