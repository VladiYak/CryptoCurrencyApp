package com.vladiyak.cryptocurrencyapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.vladiyak.cryptocurrencyapp.R
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("ViewConstructor")
class CustomMarkerView (context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    private var mOffset: MPPointF? = null
    val textViewDate = findViewById<TextView>(R.id.tvMarkerDate)
    val textViewValue = findViewById<TextView>(R.id.tvMarkerValue)

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        val sdf = SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss")
        val dateValue = String.format("%.0f", e!!.x)
        val date = Date(java.lang.Long.valueOf(dateValue))
        val formattedDate = sdf.format(date)

        textViewDate.text = formattedDate
        textViewValue.text = "$" + e!!.y

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        if (mOffset == null) {
            mOffset = MPPointF((-(width / 2.1)).toFloat(), (-height).toFloat())
        }
        return mOffset as MPPointF
    }

}