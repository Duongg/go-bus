package com.example.gobus.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gobus.R
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.view.RouteDetailsActivity
import com.example.gobus.view.adapters.BusRoutesAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.frg_bus_routes.*
import java.lang.reflect.Type


class BusRoutesFragment : Fragment(), SearchView.OnQueryTextListener {
    private var listBusRoute: List<ResponseModel.BusRoute.Result>? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var adapterBusRoute: BusRoutesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        val dataGson = bundle?.getString("DATA_BUS_ROUTE")
        val listType: Type = object : TypeToken<List<ResponseModel.BusRoute.Result?>>() {}.type
        listBusRoute = Gson().fromJson(dataGson, listType)
        val view = inflater.inflate(R.layout.frg_bus_routes, container, false)
        recyclerView = view?.findViewById(R.id.listBusRoutes)
        recyclerView?.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }
    private fun initUI() {
        if (arguments != null) {
            card.findViewById<SearchView>(R.id.search_view).setOnQueryTextListener(this)
            search_view.maxWidth = Integer.MAX_VALUE;
            search_view.setOnQueryTextListener(this)

            if (listBusRoute == null) {
                listBusRoute = emptyList()
            }
             adapterBusRoute = BusRoutesAdapter(
                activity!!.applicationContext,
                listBusRoute
            )

            recyclerView?.adapter = adapterBusRoute

            adapterBusRoute.setOnClickItemListener(object : BusRoutesAdapter.ItemBusRouteListener {
                override fun onClickItemBusRoute(position: Int, view: View) {
                    if (listBusRoute != null) {
                        val intent = Intent(
                            activity!!.applicationContext,
                            RouteDetailsActivity::class.java
                        )
                        intent.putExtra("BUS_ROUTE", listBusRoute!!.get(position))
                        startActivity(intent)
                    }
                }

            })


            Log.d("LIST FRAGMENT", listBusRoute?.size.toString())
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapterBusRoute.filter.filter(newText)
        return false
    }

}