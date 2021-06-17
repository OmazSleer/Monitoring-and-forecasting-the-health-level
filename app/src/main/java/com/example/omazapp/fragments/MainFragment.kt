package com.example.omazapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.omazapp.R
import com.example.omazapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_registrationFragment) }
        binding.button2.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_authentificationFragment) }
        return binding.root
    }

}