package com.example.omazapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.omazapp.R
import com.example.omazapp.data.user.User
import com.example.omazapp.data.user.UserViewModel
import com.example.omazapp.databinding.FragmentProfileBinding
import com.google.gson.Gson

class ProfileFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentProfileBinding
    private var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val preferences: SharedPreferences = this.requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = preferences.getString("Omaz", "")
        user = gson.fromJson<User>(json, User::class.java)
        binding.editTextAgeContainer.setText(user?.age.toString())
        binding.editTextCountryContainer.setText(user?.country)
        binding.editTextGenderContainer.setText(user?.gender)
        binding.editTextNameContainer.setText(user?.name)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_history -> {
                    findNavController().navigate(R.id.action_profileFragment_to_historyFragment)
                }
                R.id.ic_home -> {
                    findNavController().navigate(R.id.action_profileFragment_to_generalFragment)
                }
                R.id.ic_graph -> {
                    findNavController().navigate(R.id.action_profileFragment_to_pulseGraphFragment)
                }
            }
            true

        }
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.editTextAgeContainer.isEnabled = false
        binding.editTextNameContainer.isEnabled = false
        binding.editTextGenderContainer.isEnabled = false
        binding.editTextCountryContainer.isEnabled = false
        binding.buttonEdit.setOnClickListener {
            if (!binding.editTextAgeContainer.isEnabled) {
                binding.buttonEdit.setText("Save")
                binding.editTextAgeContainer.isEnabled = true
                binding.editTextNameContainer.isEnabled = true
                binding.editTextGenderContainer.isEnabled = true
                binding.editTextCountryContainer.isEnabled = true
            } else if (updateUser()) {
                binding.buttonEdit.setText("Edit")
                binding.editTextAgeContainer.isEnabled = false
                binding.editTextNameContainer.isEnabled = false
                binding.editTextGenderContainer.isEnabled = false
                binding.editTextCountryContainer.isEnabled = false
            }
        }
        binding.buttonExit.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_mainFragment) }
    }

    private fun updateUser(): Boolean {
        val name = binding.editTextNameContainer.text.toString()
        val age = Integer.parseInt(binding.editTextAgeContainer.text.toString())
        val country = binding.editTextCountryContainer.text.toString()
        val gender = binding.editTextGenderContainer.text.toString()
        return if (inputCheck(name, age.toString(), country, gender)) {
            val updatedUser =
                User(user!!.id, user!!.login, name, age, country, gender, user!!.password)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun inputCheck(
        name: String,
        age: String,
        country: String,
        gender: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) && age.isBlank() && TextUtils.isEmpty(country) && TextUtils.isEmpty(
            gender
        ))
    }


}