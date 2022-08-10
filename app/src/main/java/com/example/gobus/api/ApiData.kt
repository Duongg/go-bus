package com.example.gobus.api

import com.example.gobus.responsemodel.ResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import javax.inject.Inject

class ApiData @Inject constructor(val apiService: ApiService) {

    fun getBusRoutes(): Observable<Response<List<ResponseModel.BusRoute.Result>>> = apiService.getBusRoutes()

    fun getBusStops(): Observable<Response<List<ResponseModel.BusStop.Result>>> = apiService.getBusStops()

    fun getBusByBusRouteId(busRouteId: String): Observable<Response<List<ResponseModel.BusData.Result>>> = apiService.getBusByBusRouteID(busRouteId)
}