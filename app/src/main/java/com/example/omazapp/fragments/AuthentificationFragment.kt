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
import com.example.omazapp.R
import com.example.omazapp.data.user.User
import com.example.omazapp.data.user.UserViewModel
import com.example.omazapp.databinding.FragmentAuthentificationBinding
import com.example.omazapp.databinding.FragmentProfileBinding
import com.google.gson.Gson


class AuthentificationFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentAuthentificationBinding
    private var bol = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthentificationBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.editTextAuthentificationLogin.setText("")
        binding.editTextAuthentificationPassword.setText("")
        binding.button4.setOnClickListener {
            mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { users ->
                for (i in users) {
                    if (i.login == binding.editTextAuthentificationLogin.text.toString() && i.password == binding.editTextAuthentificationPassword.text.toString()) {
                        bol = true
                        val preferences: SharedPreferences = this.requireActivity()
                            .getSharedPreferences("pref", Context.MODE_PRIVATE)
                        val editor = preferences.edit()
                        val gson = Gson()
                        val json: String = gson.toJson(i)
                        editor.putString("Omaz", json)
                        editor.apply()
                    }
                }
                if (bol) {
                    Toast.makeText(this.requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_authentificationFragment_to_generalFragment)
                } else {
                    Toast.makeText(this.requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun readData() {

    }

}