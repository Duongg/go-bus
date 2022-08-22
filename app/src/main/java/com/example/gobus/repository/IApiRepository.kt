package com.example.gobus.repository

import com.example.gobus.data.local.entity.BoundPointEntity
import com.example.gobus.data.local.entity.BusRouteLineEntity
import com.example.gobus.responsemodel.ResponseModel
import io.reactivex.rxjava3.core.Observable

interface IApiRepository {

    fun getBusRoutes(): Observable<ResponseModel.BusRoute.ResponseSealed>

    fun getBusStops(): Observable<ResponseModel.BusStop.ResponseSealed>

    fun getBusByBusRouteID(busRouteId: String): Observable<ResponseModel.BusData.ResponseSealed>

    fun getBusRouteLine(): Observable<ResponseModel.BusRouteLine.ResponseSealed>

    fun insertBusRouteLines(busRouteLine: ResponseModel.BusRouteLine.Result)


}