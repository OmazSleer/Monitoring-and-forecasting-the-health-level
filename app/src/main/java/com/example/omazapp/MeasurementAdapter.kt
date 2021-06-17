package com.example.omazapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.omazapp.data.measurement.Measurement
import com.example.omazapp.fragments.HistoryFragmentDirections
import kotlinx.android.synthetic.main.measurement_item_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class MeasurementAdapter() : RecyclerView.Adapter<MeasurementAdapter.MyViewHolder>() {

    private var measurements = emptyList<Measurement>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.measurement_item_layout, parent, false)
        )
    }

    override fun getItemCount() = measurements.count()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = measurements[position]
        holder.itemView.tv_count_heartbeat.text = currentItem.number.toString()
        holder.itemView.tv_time.text = convertLongToTime(currentItem.timer!!.toLong())
        holder.itemView.rowLayout.setOnClickListener {
            val action =
                HistoryFragmentDirections.actionHistoryFragmentToUpdateFieldOfList(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(data: List<Measurement>) {
        this.measurements = data
        notifyDataSetChanged()
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }

    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return df.parse(date).time
    }
}