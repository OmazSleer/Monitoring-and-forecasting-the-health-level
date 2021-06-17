package com.example.omazapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.omazapp.R
import com.example.omazapp.data.measurement.MeasurementViewModel
import com.example.omazapp.data.user.User
import com.example.omazapp.databinding.FragmentTestBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import androidx.lifecycle.Observer
import java.util.*
import kotlin.math.exp


class TestFragment : Fragment() {
    private lateinit var mMeasurementViewModel: MeasurementViewModel
    private var user: User? = null
    private lateinit var binding: FragmentTestBinding
    private var number1: Int = 5
    private var number2: Int = 5
    private var number3: Int = 5
    private var number4: Int = 5
    private var number5: Int = 5
    private var number6: Int = 5
    private var number7: Int = 5
    private var number8: Int = 5
    private var number9: Int = 5
    private var number10: Int = 5
    private var number11: Int = 5
    private var seek1: SeekBar? = null
    private var seek2: SeekBar? = null
    private var seek3: SeekBar? = null
    private var seek4: SeekBar? = null
    private var seek5: SeekBar? = null
    private var seek6: SeekBar? = null
    private var seek7: SeekBar? = null
    private var seek8: SeekBar? = null
    private var seek9: SeekBar? = null
    private var seek10: SeekBar? = null
    private var seek11: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.tool_bar_profile_1)
        mMeasurementViewModel = ViewModelProvider(this).get(MeasurementViewModel::class.java)
        seek1 = view.findViewById(R.id.seekBar1)
        seek2 = view.findViewById(R.id.seekBar2)
        seek3 = view.findViewById(R.id.seekBar3)
        seek4 = view.findViewById(R.id.seekBar4)
        seek5 = view.findViewById(R.id.seekBar5)
        seek6 = view.findViewById(R.id.seekBar6)
        seek7 = view.findViewById(R.id.seekBar7)
        seek8 = view.findViewById(R.id.seekBar8)
        seek9 = view.findViewById(R.id.seekBar9)
        seek10 = view.findViewById(R.id.seekBar10)
        seek11 = view.findViewById(R.id.seekBar11)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        binding = FragmentTestBinding.inflate(inflater, container, false)
        initSeeks()
        return view
    }

    private fun initSeeks() {
        seek1!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number1 = progress

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek2!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number2 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek3!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number3 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek4!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number4 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek5!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number5 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek6!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number6 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek7!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number7 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek8!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number8 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek9!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number9 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek10!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number10 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seek11!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number11 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.test_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_reference) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
            }
            builder.setTitle("Pass the test")
            builder.setMessage("To pass the test, answer the questions (where 0 is the worst indicator, and 10 is the best)")
            builder.create().show()
        } else if (item.itemId == R.id.menu_save) {
            var result = 0.0F
            var heartbeats = 0
            var count = 0
            val time = Integer.parseInt(convertLongToTime(System.currentTimeMillis()))
            result += ((number1.toDouble() / 10) * 0.2).toFloat()
            result += ((number2.toDouble() / 10) * 0.15).toFloat()
            result += ((number3.toDouble() / 10) * 0.05).toFloat()
            result += ((number4.toDouble() / 10) * 0.1).toFloat()
            result += ((number5.toDouble() / 10) * 0.05).toFloat()
            result += ((number6.toDouble() / 10) * 0.05).toFloat()
            result += ((number7.toDouble() / 10) * 0.1).toFloat()
            result += ((number8.toDouble() / 10) * 0.05).toFloat()
            result += ((number9.toDouble() / 10) * 0.05).toFloat()
            result += ((number10.toDouble() / 10) * 0.15).toFloat()
            result += ((number11.toDouble() / 10) * 0.05).toFloat()
            mMeasurementViewModel.readAllData.observe(viewLifecycleOwner, Observer { measurement ->
                for (i in measurement) {
                    heartbeats = heartbeats?.plus(i.number!!)
                    count++
                    println(result)
                }
                heartbeats = heartbeats!!.div(count)
            })
            when (heartbeats) {
                in 0..64 -> {
                    result = (result * 0.85).toFloat()
                }
                in 65..80 -> {
                    result = (result * 0.9).toFloat()
                }
                in 81..110 -> {
                    result = (result * 0.95).toFloat()
                }
            }

            when (time) {
                0 -> result = (result * 0.2).toFloat()
                1 -> result = (result * 0.18).toFloat()
                2 -> result = (result * 0.15).toFloat()
                3 -> result = (result * 0.2).toFloat()
                4 -> result = (result * 0.25).toFloat()
                5 -> result = (result * 0.35).toFloat()
                6 -> result = (result * 0.45).toFloat()
                7 -> result = (result * 0.55).toFloat()
                8 -> result = (result * 0.65).toFloat()
                9 -> result = (result * 0.75).toFloat()
                10 -> result = (result * 0.85).toFloat()
                11 -> result = (result * 0.9).toFloat()
                12 -> result = (result * 0.85).toFloat()
                13 -> result = (result * 0.8).toFloat()
                14 -> result = (result * 0.75).toFloat()
                15 -> result = (result * 0.65).toFloat()
                16 -> result = (result * 0.6).toFloat()
                17 -> result = (result * 0.55).toFloat()
                18 -> result = (result * 0.53).toFloat()
                19 -> result = (result * 0.55).toFloat()
                20 -> result = (result * 0.58).toFloat()
                21 -> result = (result * 0.6).toFloat()
                22 -> result = (result * 0.4).toFloat()
                23 -> result = (result * 0.3).toFloat()
                24 -> result = (result * 0.2).toFloat()
            }
            result = (7 * result - 2)
            val action = TestFragmentDirections.actionTestFragmentToGeneralFragment(result)
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH")
        return format.format(date)
    }


}