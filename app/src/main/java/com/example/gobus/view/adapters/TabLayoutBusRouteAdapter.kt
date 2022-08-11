package com.example.gobus.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.gobus.view.fragments.BusRouteInformationDetailsFragment
import com.example.gobus.view.fragments.BusStopFragment

class TabLayoutBusRouteAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTab: Int
) :FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                BusStopFragment()
            }
            1 -> {
                BusRouteInformationDetailsFragment()
            }
            else ->{
                getItem(position)
            }
        }
    }
    override fun getCount(): Int {
      return totalTab
    }

}