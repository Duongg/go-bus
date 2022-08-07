package com.example.gobus.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gobus.R
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.view.adapters.BusStopsAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class BusStopFragment : Fragment() {

    private var listBusStop: List<ResponseModel.BusStop.Result>? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        var dataGson = bundle?.getString("DATA_BUS_STOPS")
        val listType: Type = object : TypeToken<List<ResponseModel.BusStop.Result?>>() {}.type
        listBusStop = Gson().fromJson(dataGson, listType)
        var view = inflater.inflate(R.layout.frg_bus_stop, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }
    private fun initUI() {
        if(arguments != null) {
            Log.d("LIST", listBusStop?.size.toString())
            recyclerView = view?.findViewById(R.id.rvListBusStops)
            if (listBusStop == null) {
                listBusStop = emptyList()
            }
            val adapterBusStop = BusStopsAdapter(
                activity!!.applicationContext,
                listBusStop
            )
            recyclerView?.layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
            recyclerView?.adapter = adapterBusStop
            adapterBusStop.notifyDataSetChanged()

        }
    }

}