package com.example.gobus.repository

import com.example.gobus.responsemodel.ResponseModel
import io.reactivex.rxjava3.core.Observable

interface IApiRepository {

    fun getBusRoutes(): Observable<ResponseModel.BusRoute.ResponseSealed>

    fun getBusStops(): Observable<ResponseModel.BusStop.ResponseSealed>
}