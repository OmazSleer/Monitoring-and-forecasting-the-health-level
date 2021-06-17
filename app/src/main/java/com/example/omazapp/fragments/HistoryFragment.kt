package com.example.omazapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.omazapp.MeasurementAdapter
import com.example.omazapp.R
import com.example.omazapp.data.measurement.MeasurementViewModel
import com.example.omazapp.data.user.User
import com.example.omazapp.databinding.FragmentHistoryBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_history.view.*


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var mMeasurementViewModel: MeasurementViewModel
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(view.findViewById<Toolbar>(R.id.tool_bar_history_2))
        view.fab_add_measurement.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_createFieldOfList)
        }
         view.findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> {
                    findNavController().navigate(R.id.action_historyFragment_to_generalFragment)
                }
                R.id.ic_person -> {
                    findNavController().navigate(R.id.action_historyFragment_to_profileFragment)
                }
                R.id.ic_graph -> {
                    findNavController().navigate(R.id.action_historyFragment_to_pulseGraphFragment)
                }
            }
            true
        }
        val preferences: SharedPreferences = this.requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = preferences.getString("Omaz", "")
        user = gson.fromJson(json, User::class.java)
        val adapter = MeasurementAdapter()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        user = gson.fromJson(json, User::class.java)
        mMeasurementViewModel = ViewModelProvider(this).get(MeasurementViewModel::class.java)
        mMeasurementViewModel.readAllData.observe(viewLifecycleOwner, Observer { measurement ->
            val mes = measurement.filter { it.userId == user!!.id }
            adapter.setData(mes)
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            mMeasurementViewModel.deleteAllMeasurement()
        }
        return super.onOptionsItemSelected(item)
    }



}