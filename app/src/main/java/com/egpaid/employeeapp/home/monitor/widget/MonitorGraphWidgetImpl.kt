package com.egpaid.employeeapp.home.monitor.widget

import android.content.Context
import android.graphics.Color
import android.view.View
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.container_parent_child_graph.*
import javax.inject.Inject


class MonitorGraphWidgetImpl @Inject constructor(val context: Context) :
    MonitorGraphWidget, OnChartValueSelectedListener {

    companion object {
        const val NUMBER_BAR = 2
    }

    override lateinit var containerView: View

    override fun initView(contentView: View) {
        this.containerView = contentView
        showGraph()
    }

    override fun showLoading() {
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }

    override fun onNothingSelected() {
    }


    override fun setContent(content: List<Monitor>) {
    }

    override fun showGeneralError() {
    }

    override fun showNetworkError() {
    }

    override fun onRetry(action: () -> Unit) {
    }

    override fun show() {
    }

    override fun hide() {
    }




    private fun showGraph() {
        chart.setDrawBarShadow(false)
        chart.description.isEnabled = false
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(true)
        // empty labels so that the names are spread evenly
        // empty labels so that the names are spread evenly
        val labels = arrayOf("", "Name1", "Name2", "Name3", "Name4", "Name5", "")
        val xAxis: XAxis = chart.xAxis
        xAxis.setCenterAxisLabels(true)
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(true)
        xAxis.granularity = 1f // only intervals of 1 day

        xAxis.textColor = Color.BLACK
        xAxis.textSize = 12f
        xAxis.axisLineColor = Color.WHITE
        xAxis.axisMinimum = 1f
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        val leftAxis: YAxis = chart.axisLeft
        leftAxis.textColor = Color.BLACK
        leftAxis.textSize = 12f
        leftAxis.axisLineColor = Color.WHITE
        leftAxis.setDrawGridLines(true)
        leftAxis.granularity = 2f
        leftAxis.setLabelCount(8, true)
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)

        chart.axisRight.isEnabled = false
        chart.legend.isEnabled = false

        val valOne = floatArrayOf(10f, 20f, 30f, 40f, 50f)
        val valTwo = floatArrayOf(60f, 50f, 40f, 30f, 20f)
//        val valThree = floatArrayOf(50f, 60f, 20f, 10f, 30f)

        val barOne: ArrayList<BarEntry> = ArrayList()
        val barTwo: ArrayList<BarEntry> = ArrayList()
//        val barThree: ArrayList<BarEntry> = ArrayList()
        for (i in valOne.indices) {
            barOne.add(BarEntry(valOne[i], valOne[i]))
            barTwo.add(BarEntry(valOne[i], valTwo[i]))
//            barThree.add(BarEntry(valOne[i], valThree[i]))
        }

        val set1 = BarDataSet(barOne, "barOne")
        set1.color = Color.BLUE
        val set2 = BarDataSet(barTwo, "barTwo")
        set2.color = Color.MAGENTA
//        val set3 = BarDataSet(barThree, "barTwo")
//        set2.color = Color.GREEN

        set1.isHighlightEnabled = false
        set2.isHighlightEnabled = false
//        set3.isHighlightEnabled = false
        set1.setDrawValues(false)
        set2.setDrawValues(false)
//        set3.setDrawValues(false)

        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(set1)
        dataSets.add(set2)
//        dataSets.add(set3)
        val data = BarData(dataSets)
        val groupSpace = 0.4f
        val barSpace = 0f
        val barWidth = 0.3f
        // (barSpace + barWidth) * 2 + groupSpace = 1
        // (barSpace + barWidth) * 2 + groupSpace = 1
        data.barWidth = barWidth
        // so that the entire chart is shown when scrolled from right to left
        // so that the entire chart is shown when scrolled from right to left
        xAxis.axisMaximum = labels.size - 1.1f
        chart.data = data
        chart.setScaleEnabled(false)
        chart.setVisibleXRangeMaximum(6f)
        chart.groupBars(1f, groupSpace, barSpace)
        chart.invalidate()
    }



}