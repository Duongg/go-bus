package com.example.gobus.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gobus.R
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.utils.parseDateTimeToTime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_bus_route_information_details.*
import java.lang.reflect.Type


class BusRouteInformationDetailsFragment : Fragment() {

    private var busRoute: ResponseModel.BusRoute.Result? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bundle = this.arguments
        val dataGson = bundle?.getString("BUS_ROUTE")
        busRoute = Gson().fromJson(dataGson, ResponseModel.BusRoute.Result::class.java)
        Log.d("LIST BUS ROUTE" , busRoute.toString())
        val view = inflater.inflate(R.layout.fragment_bus_route_information_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }
    fun setUI(){
        if(busRoute != null){
            txtRouteNo.text = busRoute?.RouteNo ?: ""
            txtRouteName.text = busRoute?.RouteName ?: ""
            txtTripPerDay.text = busRoute?.TripsPerDay.toString()
            txtDepartureTime.text = parseDateTimeToTime(busRoute?.FirstDepartureTime) + "-" + parseDateTimeToTime(
                busRoute?.LastDepartureTime
            )
            txtRouteInBound.text = busRoute?.RouteInBound
            txtRouteOutBound.text = busRoute?.RouteOutBound
        }
    }

}