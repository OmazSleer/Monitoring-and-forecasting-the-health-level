package com.example.omazapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.omazapp.R
import com.example.omazapp.data.user.User
import com.example.omazapp.data.user.UserViewModel
import com.example.omazapp.databinding.FragmentRegistrationBinding
import com.google.gson.Gson


class RegistrationFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.buttonRegistration.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val name = binding.editTextName.text.toString()
        val age = binding.editTextAge.text.toString()
        val country = binding.editTextCountry.text.toString()
        val gender = if (binding.checkBoxMale.isChecked)
         "Male"
        else "Female"

        val password = binding.editTextPassword.text.toString()
        val login = binding.editTextLogin.text.toString()
        if (inputCheck(name, age, country, gender, password, login)) {
            val user = User(0,login, name, age.toInt(), country, gender, password)
            mUserViewModel.addUser(user)
            val preferences: SharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
            val editor = preferences.edit()
            val gson = Gson()
            val json: String = gson.toJson(user)
            editor.putString("Omaz", json)
            editor.commit()
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registrationFragment_to_generalFragment)
        } else {
            Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(
        name: String,
        age: String,
        country: String,
        gender: String,
        password: String,
        login: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) && age.isBlank() && TextUtils.isEmpty(country) && TextUtils.isEmpty(
            gender
        ) && TextUtils.isEmpty(password) && TextUtils.isEmpty(login))
    }

}