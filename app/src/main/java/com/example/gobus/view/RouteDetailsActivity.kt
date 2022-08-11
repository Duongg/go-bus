package com.example.gobus.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gobus.R
import com.example.gobus.extension.getViewModel
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.utils.HandleFailAndErrorResponse
import com.example.gobus.view.adapters.TabLayoutBusRouteAdapter
import com.example.gobus.view.fragments.BusRouteInformationDetailsFragment
import com.example.gobus.view.fragments.BusStopFragment
import com.example.gobus.view.fragments.MapBusStopFragment
import com.example.gobus.viewmodel.GetBusByRouteIdViewModel
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_route_details.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class RouteDetailsActivity : AppCompatActivity() {
    private var busRoute: ResponseModel.BusRoute.Result? = null
    private var getBusByRouteIdViewModel: GetBusByRouteIdViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_details)
        busRoute = intent.getSerializableExtra("BUS_ROUTE") as ResponseModel.BusRoute.Result
        val nameRoute = busRoute?.RouteName
        if(nameRoute!!.length > 25){
            val nameCutFirst = nameRoute.take(25)
            val nameCutLast = nameRoute.takeLast(5)
            toolbar_title.text = "Tuyến ${nameCutFirst}"+"..." + nameCutLast
        }else{
            toolbar_title.text = "Tuyến ${nameRoute}"
        }

        toolbar_button.setOnClickListener {
            onBackPressed()
        }
        initUI()
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
    fun initUI(){
        tbBusRouteDetailsLayout.addTab(tbBusRouteDetailsLayout.newTab().setText("Trạm dừng"))
        tbBusRouteDetailsLayout.addTab(tbBusRouteDetailsLayout.newTab().setText("Thông tin"))

        val tabLayoutAdapter = TabLayoutBusRouteAdapter(this,supportFragmentManager, tbBusRouteDetailsLayout.tabCount )
        viewPagerBusRouteDetails.adapter = tabLayoutAdapter
        viewPagerBusRouteDetails.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tbBusRouteDetailsLayout))
        tbBusRouteDetailsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerBusRouteDetails.currentItem = tab.position
                if(viewPagerBusRouteDetails.currentItem == 0){
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frameLayoutBusRouteDetails, BusStopFragment())
                    fragmentTransaction.commit()
                }else if(viewPagerBusRouteDetails.currentItem == 1){
                    setDataForBusRouteInformationDetail()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }
    fun setDataForBusRouteInformationDetail(){
        val busRouteInformationFragment = BusRouteInformationDetailsFragment()
        val bundle = Bundle()
        viewPagerBusRouteDetails.visibility = View.GONE
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutBusRouteDetails, busRouteInformationFragment)
        bundle.putString("BUS_ROUTE", Gson().toJson(busRoute))
        busRouteInformationFragment.arguments = bundle
        fragmentTransaction.commit()
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}