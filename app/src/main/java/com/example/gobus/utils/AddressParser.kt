package com.example.gobus.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.vision.barcode.Barcode.GeoPoint
import java.io.IOException



@SuppressLint("RestrictedApi")
fun getLocationFromAddress(strAddress: String?, context: Context): GeoPoint? {
    val coder = Geocoder(context)
    val address: List<Address>?
    var p1: GeoPoint? = null
    try {
        address = coder.getFromLocationName(strAddress, 5)
        if (address == null) {
            return null
        }
        val location: Address = address[0]
        location.getLatitude()
        location.getLongitude()
        p1 = GeoPoint(
            (location.getLatitude() * 1E6) as Double,
            (location.getLongitude() * 1E6) as Double
        )
        return p1
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}