package com.example.omazapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.omazapp.R
import com.example.omazapp.data.measurement.Measurement
import com.example.omazapp.data.measurement.MeasurementViewModel
import com.example.omazapp.data.reliabilityindicator.Indicator
import com.example.omazapp.data.reliabilityindicator.IndicatorViewModel
import com.example.omazapp.data.user.User
import com.example.omazapp.databinding.FragmentGeneralBinding
import com.google.gson.Gson
import java.lang.reflect.InvocationTargetException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.exp


class GeneralFragment : Fragment() {
    private lateinit var binding: FragmentGeneralBinding
    private val args by navArgs<GeneralFragmentArgs>()
    private lateinit var mMeasurementViewModel: MeasurementViewModel
    private lateinit var mIndicatorViewModel: IndicatorViewModel
    private var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGeneralBinding.inflate(inflater, container, false)
        mMeasurementViewModel = ViewModelProvider(this).get(MeasurementViewModel::class.java)
        mIndicatorViewModel = ViewModelProvider(this).get(IndicatorViewModel::class.java)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_history -> {
                    findNavController().navigate(R.id.action_generalFragment_to_historyFragment)
                }
                R.id.ic_person -> {
                    findNavController().navigate(R.id.action_generalFragment_to_profileFragment)
                }
                R.id.ic_graph -> {
                    findNavController().navigate(R.id.action_generalFragment_to_pulseGraphFragment)
                }
            }
            true

        }
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.buttonConncetion.setOnClickListener {
            findNavController().navigate(R.id.action_generalFragment_to_testFragment)
        }

        binding.buttonGetData.setOnClickListener {
            val preferences: SharedPreferences = this.requireActivity()
                .getSharedPreferences("pref", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = preferences.getString("Omaz", "")
            user = gson.fromJson(json, User::class.java)
            mMeasurementViewModel.deleteAllMeasurement()
            for (i in 0..9) {
                val number: Int = (60..110).random()
                mMeasurementViewModel.addMeasurement(
                    Measurement(
                        0,
                        user!!.id,
                        number,
                        System.currentTimeMillis() - (0..3600000).random()
                    )
                )
            }
            val handler = android.os.Handler()
            handler.postDelayed(
                { Toast.makeText(requireContext(), "Import Success", Toast.LENGTH_SHORT).show() },
                1000
            )
        }

        binding.buttonAnalyze.setOnClickListener {
            val preferences: SharedPreferences = this.requireActivity()
                .getSharedPreferences("pref", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = preferences.getString("Omaz", "")
            user = gson.fromJson(json, User::class.java)
            try {
                    val result = exp(-exp(-args.testResult))
                    mIndicatorViewModel.addIndicator(
                        Indicator(
                            0,
                            user!!.id,
                            result,
                            System.currentTimeMillis()
                        )
                    )
                    Toast.makeText(
                        requireContext(),
                        "Succes, indicator saved in graphs",
                        Toast.LENGTH_SHORT
                    ).show()
            } catch (e : InvocationTargetException) {
                Toast.makeText(
                    requireContext(),
                    "Fail, pass the test",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


}