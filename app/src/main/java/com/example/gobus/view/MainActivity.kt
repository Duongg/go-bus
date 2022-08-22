package com.example.gobus.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gobus.R
import com.example.gobus.data.local.entity.BoundPointEntity
import com.example.gobus.data.local.entity.BusRouteLineEntity
import com.example.gobus.extension.getViewModel
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.utils.HandleFailAndErrorResponse
import com.example.gobus.view.adapters.TabLayoutAdapter
import com.example.gobus.view.fragments.BusRoutesFragment
import com.example.gobus.view.fragments.BusStopFragment
import com.example.gobus.view.fragments.MapBusStopFragment
import com.example.gobus.viewmodel.GetBusRoutesViewModel
import com.example.gobus.viewmodel.GetBusStopsViewModel
import com.example.gobus.viewmodel.GetRouteLineViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class MainActivity : AppCompatActivity() {
    private var viewModel: GetBusRoutesViewModel? = null
    private var getBusStopsViewModel: GetBusStopsViewModel? = null
    private var getBusRoutesViewModel: GetBusRoutesViewModel? = null
    private var getBusRouteLineViewModel: GetRouteLineViewModel? = null
    private var listBusStop: List<ResponseModel.BusStop.Result>? = null
    private var listBusRoutes: List<ResponseModel.BusRoute.Result>? = null
    private var listBusRouteLine: List<ResponseModel.BusRouteLine.Result>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        initDataBusRouteLine()
    }


    private fun initUI(){
        toolbar_title.text = "Trang chủ"
        toolbar_button.visibility = View.GONE
        tbMainLayout.addTab(tbMainLayout.newTab().setText("Xung quanh"))
        tbMainLayout.addTab(tbMainLayout.newTab().setText("Tuyến xe"))
        tbMainLayout.tabGravity = TabLayout.GRAVITY_FILL

        val tabLayoutAdapter = TabLayoutAdapter(this,supportFragmentManager, tbMainLayout.tabCount )
        viewPagerMain.adapter = tabLayoutAdapter

        viewPagerMain.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tbMainLayout))
        tbMainLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerMain.currentItem = tab.position
                if(viewPagerMain.currentItem == 0){
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frameLayout, MapBusStopFragment())
                    fragmentTransaction.commit()
                }else if (viewPagerMain.currentItem == 1){
                    initDataBusRoutes()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    private fun initDataBusStops(){

        getBusStopsViewModel = getViewModel()

        getBusStopsViewModel?.getBusStops()

        getBusStopsViewModel?.getBusStopsViewModelLiveData?.observe(this,{ it ->
            when{
                it.isError.isError ->{
                    HandleFailAndErrorResponse.handleError(this, it.isError.exception)
                }
                it.isFail.isFail ->{
                    HandleFailAndErrorResponse.handleFail(this, it.isFail)
                }
                else ->{
                    it.data.also {
                        Log.d("TAG", it?.get(0)?.Name.toString())
                        listBusStop = it
                        val bundle = Bundle()
                        var busStopFragment = BusStopFragment()
                        if(listBusStop != null) {
                            viewPagerMain.visibility = View.GONE
                            var fragmentTransaction = supportFragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.frameLayout, busStopFragment)
                            bundle.putString("DATA_BUS_STOPS", Gson().toJson(listBusStop))
                            busStopFragment.arguments = bundle
                            fragmentTransaction.commit()
                        }else{
                            listBusStop = emptyList()
                        }
                    }
                }

            }
        })
    }
    private fun initDataBusRoutes(){
        getBusRoutesViewModel = getViewModel()

        getBusRoutesViewModel?.getBusRoutes()

        getBusRoutesViewModel?.getBusRoutesViewModelLiveData?.observe(this,{
            when {
                it.isError.isError ->{
                    HandleFailAndErrorResponse.handleError(this,it.isError.exception)
                }
                it.isFail.isFail ->{
                    HandleFailAndErrorResponse.handleFail(this, it.isFail)
                }
                else ->{
                    it.data.also {
                        listBusRoutes = it
                        val bundle = Bundle()
                        var busRoutesFragment = BusRoutesFragment()
                        Log.d("TAG", listBusRoutes?.size.toString())
                        if(listBusRoutes != null) {
                            viewPagerMain.visibility = View.GONE
                            var fragmentTransaction = supportFragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.frameLayout, busRoutesFragment)
                            bundle.putString("DATA_BUS_ROUTE", Gson().toJson(listBusRoutes))
                            busRoutesFragment.arguments = bundle
                            fragmentTransaction.commit()
                        }else{
                            listBusStop = emptyList()
                        }
                    }
                }
            }
        })

    }
    private fun initDataBusRouteLine(){
        getBusRouteLineViewModel = getViewModel()

        getBusRouteLineViewModel?.getBusRouteLine()

        getBusRouteLineViewModel?.getBusRouteLineViewModelLiveData?.observe(this,{ it ->
            when{
                it.isError.isError ->{
                    HandleFailAndErrorResponse.handleError(this, it.isError.exception)
                }
                it.isFail.isFail ->{
                    HandleFailAndErrorResponse.handleFail(this, it.isFail)
                }
                else -> {
                    it.data.also {
                        listBusRouteLine = it
                        if(listBusRouteLine != null){
                            for (item in listBusRouteLine!!) {
                                getBusRouteLineViewModel?.insertBusRouteLine(item)
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getBusStopsViewModel?.getBusStops()
    }




}