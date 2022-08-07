package com.example.gobus.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.gobus.R
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.utils.parseDateTimeToTime
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_bus_route.view.*


class BusRoutesAdapter(
    private val context: Context,
    private val listBusRoutes: List<ResponseModel.BusRoute.Result>?
) : RecyclerView.Adapter<BusRoutesAdapter.BusRouteViewHolder>(), Filterable {
    var listBusRoutesFiltered: ArrayList<ResponseModel.BusRoute.Result> = ArrayList()
    lateinit var mItemBusRouteListener: ItemBusRouteListener

    fun setOnClickItemListener(itemBusRouteListener: ItemBusRouteListener) {
        mItemBusRouteListener = itemBusRouteListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bus_route, parent, false)
        return BusRouteViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BusRouteViewHolder, position: Int) {
        val itemBusRoute = listBusRoutes?.get(position)
        holder.txtRouteNo.text = itemBusRoute?.RouteNo
        holder.txtRouteName.text = itemBusRoute?.RouteName
        holder.txtTripPerDay.text = itemBusRoute?.TripsPerDay.toString()
        holder.txtDepartureTime.text =
            parseDateTimeToTime(itemBusRoute?.FirstDepartureTime) + "-" + parseDateTimeToTime(
                itemBusRoute?.LastDepartureTime
            )
    }

    override fun getItemCount(): Int {
        if (listBusRoutes != null) {
            return listBusRoutes.size
        }
        return 0
    }


    inner class BusRouteViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val txtRouteNo: TextView = view.findViewById(R.id.txtRouteNo)
        val txtRouteName: TextView = view.findViewById(R.id.txtRouteName)
        val txtTripPerDay: TextView = view.findViewById(R.id.txtTripPerDay)
        val txtDepartureTime: TextView = view.findViewById(R.id.txtDepartureTime)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            mItemBusRouteListener.onClickItemBusRoute(adapterPosition, v)
        }
    }

    interface ItemBusRouteListener {
        fun onClickItemBusRoute(position: Int, view: View)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                listBusRoutesFiltered =
                    if (charString.isEmpty()) listBusRoutes as ArrayList<ResponseModel.BusRoute.Result> else {
                        val filteredList = ArrayList<ResponseModel.BusRoute.Result>()
                        listBusRoutes?.filter {
                            (it.RouteName?.toLowerCase()?.contains(constraint.toString()) == true)

                        }
                            ?.forEach { filteredList.add(it) }
                        filteredList

                    }
                return FilterResults().apply {
                    values = listBusRoutesFiltered
                    Log.d("LIST", Gson().toJson(listBusRoutesFiltered))
                }
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                listBusRoutesFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<ResponseModel.BusRoute.Result>
                notifyDataSetChanged()
            }

        }
    }

}