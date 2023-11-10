package com.vladiyak.cryptocurrencyapp.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class XAxisValueFormatter() : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd, HH:mm")
        val dateValue = String.format("%.0f", value)
        val date = Date(dateValue.toLong())
        return sdf.format(date)
    }
}