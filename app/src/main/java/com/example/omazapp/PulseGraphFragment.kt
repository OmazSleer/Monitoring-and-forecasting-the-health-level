package com.example.omazapp

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.omazapp.R
import com.example.omazapp.data.reliabilityindicator.IndicatorViewModel
import com.example.omazapp.databinding.FragmentPulseGraphBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_pulse_graph.*
import java.security.KeyStore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.exp
import kotlin.math.pow


class PulseGraphFragment : Fragment() {
    private lateinit var mIndicatorViewModel: IndicatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pulse_graph, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(view.findViewById<Toolbar>(R.id.tool_bar_history_3))
        mIndicatorViewModel = ViewModelProvider(this).get(IndicatorViewModel::class.java)
        view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.ic_history -> {
                        findNavController().navigate(R.id.action_pulseGraphFragment_to_historyFragment)
                    }
                    R.id.ic_person -> {
                        findNavController().navigate(R.id.action_pulseGraphFragment_to_profileFragment)
                    }
                    R.id.ic_home -> {
                        findNavController().navigate(R.id.action_pulseGraphFragment_to_generalFragment)
                    }
                }
                true
            }
        view.findViewById<Button>(R.id.buttonMonitor).setOnClickListener {
            val xValue = listOf<Float>(
                0f,
                1f,
                2f,
                3f,
                4f,
                5f,
                6f,
                7f,
                8f,
                9f,
                10f,
                11f,
                12f,
                13f,
                14f,
                15f,
                16f,
                17f,
                18f,
                19f,
                20f,
                21f,
                22f,
                23f,
                24f
            )
            val lineEntry = ArrayList<Entry>()
            mIndicatorViewModel.readAllData.observe(
                viewLifecycleOwner,
                Observer { measurement ->
                    for (i in measurement) {
                        lineEntry.add(
                            Entry(
                                convertLongToTime(i.timer!!).toFloat(),
                                i.number!!
                            )
                        )
                    }
                })

            val lineDataSet = ScatterDataSet(lineEntry, "Monitoring")
            lineDataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
            val dataSetList = ArrayList<IScatterDataSet>()
            dataSetList.add(lineDataSet)

            val data = ScatterData(dataSetList)

            lineChart.data = data
            lineChart.invalidate()


        }

        view.findViewById<Button>(R.id.buttonPredict).setOnClickListener {
            val xValue = listOf<Float>(
                0f,
                1f,
                2f,
                3f,
                4f,
                5f,
                6f,
                7f,
                8f,
                9f,
                10f,
                11f,
                12f,
                13f,
                14f,
                15f,
                16f,
                17f,
                18f,
                19f,
                20f,
                21f,
                22f,
                23f,
                24f
            )
            var averageValue: Float = 0f
            var count = 0
            val lineEntry = ArrayList<Entry>()
            mIndicatorViewModel.readAllData.observe(
                viewLifecycleOwner,
                Observer { measurement ->
                    for (i in measurement) {
                        averageValue = averageValue?.plus(i.number!!)
                        count++
                    }
                    averageValue = averageValue!!.div(count)
                })
            for (i in xValue) {
                var result = 0f
                val temp = i.toInt()
                when (temp) {
                    0 -> result = (averageValue * 0.2).toFloat()
                    1 -> result = (averageValue * 0.18).toFloat()
                    2 -> result = (averageValue * 0.15).toFloat()
                    3 -> result = (averageValue * 0.2).toFloat()
                    4 -> result = (averageValue * 0.25).toFloat()
                    5 -> result = (averageValue * 0.35).toFloat()
                    6 -> result = (averageValue * 0.45).toFloat()
                    7 -> result = (averageValue * 0.55).toFloat()
                    8 -> result = (averageValue * 0.65).toFloat()
                    9 -> result = (averageValue * 0.75).toFloat()
                    10 -> result = (averageValue * 0.85).toFloat()
                    11 -> result = (averageValue * 0.9).toFloat()
                    12 -> result = (averageValue * 0.85).toFloat()
                    13 -> result = (averageValue * 0.8).toFloat()
                    14 -> result = (averageValue * 0.75).toFloat()
                    15 -> result = (averageValue * 0.65).toFloat()
                    16 -> result = (averageValue * 0.6).toFloat()
                    17 -> result = (averageValue * 0.55).toFloat()
                    18 -> result = (averageValue * 0.53).toFloat()
                    19 -> result = (averageValue * 0.55).toFloat()
                    20 -> result = (averageValue * 0.58).toFloat()
                    21 -> result = (averageValue * 0.6).toFloat()
                    22 -> result = (averageValue * 0.4).toFloat()
                    23 -> result = (averageValue * 0.3).toFloat()
                    24 -> result = (averageValue * 0.2).toFloat()
                }
                lineEntry.add(
                    Entry(
                        i,
                        result
                    )
                )
            }
            val lineDataSet = LineDataSet(lineEntry, "Predict")
            lineDataSet.color = resources.getColor(R.color.black)
            lineDataSet.valueTextSize = 14f

            val data = LineData(lineDataSet)

            lineChart2.data = data
            lineChart2
                .setBackgroundColor(resources.getColor(R.color.white))
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            mIndicatorViewModel.deleteAllIndicator()
        }
        return super.onOptionsItemSelected(item)
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH")
        return format.format(date)
    }

}