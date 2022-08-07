package com.example.gobus.api

import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.utils.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiService {

    @GET(ApiConstants.BUS_STOPS)
    @Headers(Constants.CONTENT_TYPE_APPLICATION_JSON)
    fun getBusStops(): Observable<Response<List<ResponseModel.BusStop.Result>>>

    @GET(ApiConstants.BUS_ROUTES)
    @Headers(Constants.CONTENT_TYPE_APPLICATION_JSON)
    fun getBusRoutes(): Observable<Response<List<ResponseModel.BusRoute.Result>>>



}