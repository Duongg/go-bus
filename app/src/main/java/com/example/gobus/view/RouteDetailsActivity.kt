package com.example.gobus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.gobus.R
import com.example.gobus.extension.getViewModel
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.utils.HandleFailAndErrorResponse
import com.example.gobus.view.fragments.BusStopFragment
import com.example.gobus.viewmodel.GetBusByRouteIdViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class RouteDetailsActivity : AppCompatActivity() {
    private var busRoute: ResponseModel.BusRoute.Result? = null
    private var getBusByRouteIdViewModel: GetBusByRouteIdViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_details)
        busRoute = intent.getSerializableExtra("BUS_ROUTE") as ResponseModel.BusRoute.Result
        toolbar_title.text = "Tuyến ${busRoute?.RouteName}"
        toolbar_button.setOnClickListener {
            onBackPressed()
        }
        initData()

    }
    fun initData(){
        getBusByRouteIdViewModel = getViewModel()

        getBusByRouteIdViewModel?.getBusByRouteId(busRoute?.BusRouteId.toString())

        getBusByRouteIdViewModel?.getBusByRouteIdViewModelLiveData?.observe(this,{ it ->
            when{
                it.isError.isError ->{
                    HandleFailAndErrorResponse.handleError(this, it.isError.exception)
                }
                it.isFail.isFail ->{
                    HandleFailAndErrorResponse.handleFail(this, it.isFail)
                }
                else ->{
                    it.data.also {
                        Log.e("DATA", it.toString())
                    }
                }

            }
        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}