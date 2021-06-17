package com.example.omazapp.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.omazapp.R
import com.example.omazapp.data.measurement.Measurement
import com.example.omazapp.data.measurement.MeasurementViewModel
import com.example.omazapp.data.user.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_update_field_of_list.*
import kotlinx.android.synthetic.main.fragment_update_field_of_list.view.*


class UpdateFieldOfList : Fragment() {

    private val args by navArgs<UpdateFieldOfListArgs>()
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
        val view = inflater.inflate(R.layout.fragment_update_field_of_list, container, false)
        view.editTextPulseUpdate.setText(args.currentMeasurement.number.toString())
        (requireActivity() as AppCompatActivity).setSupportActionBar(view.findViewById<Toolbar>(R.id.tool_bar_update_field))
        view.bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_save -> {
                    updateItem()
                }
                R.id.ic_cancel -> {
                    findNavController().navigate(R.id.action_updateFieldOfList_to_historyFragment)
                }
            }
            true
        }
        mMeasurementViewModel = ViewModelProvider(this).get(MeasurementViewModel::class.java)
        view.floatingDelete.setOnClickListener {
            deleteItem()
        }
        return view
    }

    private fun updateItem() {
        val number = Integer.parseInt(editTextPulseUpdate.text.toString())
        if (inputCheck(number.toString())) {
            val updatedMeasurement = Measurement(
                args.currentMeasurement.id,
                args.currentMeasurement.userId,
                number,
                args.currentMeasurement.timer
            )
            mMeasurementViewModel.updateMeasurement(updatedMeasurement)
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFieldOfList_to_historyFragment)
        } else Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            mMeasurementViewModel.deleteMeasurement(args.currentMeasurement)
            findNavController().navigate(R.id.action_updateFieldOfList_to_historyFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun inputCheck(
        number: String
    ): Boolean {
        return !(TextUtils.isEmpty(number))
    }

    private fun deleteItem() {
        mMeasurementViewModel.deleteMeasurement(args.currentMeasurement)
        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateFieldOfList_to_historyFragment)
    }

}