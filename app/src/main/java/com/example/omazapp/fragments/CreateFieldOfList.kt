package com.example.omazapp.fragments

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.omazapp.R
import com.example.omazapp.data.measurement.Measurement
import com.example.omazapp.data.measurement.MeasurementViewModel
import com.example.omazapp.data.user.User
import com.example.omazapp.databinding.FragmentCreateFieldOfListBinding
import com.google.gson.Gson


class CreateFieldOfList : Fragment() {
    private lateinit var mMeasurementViewModel: MeasurementViewModel
    private lateinit var binding: FragmentCreateFieldOfListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateFieldOfListBinding.inflate(inflater, container, false)
        mMeasurementViewModel = ViewModelProvider(this).get(MeasurementViewModel::class.java)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_cancel -> {
                    findNavController().navigate(R.id.action_createFieldOfList_to_historyFragment)
                }
                R.id.ic_save -> {
                    insertDataToDatabase()
                    findNavController().navigate(R.id.action_createFieldOfList_to_historyFragment)
                }
            }
            true
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val preferences: SharedPreferences = this.requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = preferences.getString("Omaz", "")
        val user = gson.fromJson<User>(json, User::class.java)
        val pulse = binding.editTextPulseCreate.text.toString()
        if (inputCheck(pulse)) {
            val measurement = Measurement(0, user.id, Integer.parseInt(pulse), System.currentTimeMillis())
            mMeasurementViewModel.addMeasurement(measurement)
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(
        pulse: String
    ): Boolean {
        return pulse.isNotBlank()
    }

}