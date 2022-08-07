package com.example.gobus.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gobus.R
import com.example.gobus.responsemodel.ResponseModel

class BusStopsAdapter(
    private val context: Context,
    private val listBusStops: List<ResponseModel.BusStop.Result>?
):  RecyclerView.Adapter<BusStopsAdapter.BusStopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_bus_stop, parent, false)
        return BusStopViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val itemBusStops = listBusStops?.get(position)
        holder.textName.text = itemBusStops?.Name
        holder.textPlace.text = itemBusStops?.PlaceNameFromName

    }

    override fun getItemCount(): Int  {
        if (listBusStops != null) {
            return listBusStops.size
        }
        return 0
    }

    inner class BusStopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.txtName)
        val textPlace: TextView = view.findViewById(R.id.txtPlaceNameFromName)
    }
}