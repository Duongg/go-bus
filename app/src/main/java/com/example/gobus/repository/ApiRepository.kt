package com.example.gobus.repository

import android.util.Log
import com.example.gobus.api.ApiData
import com.example.gobus.extension.observeAndSubcribeOn
import com.example.gobus.responsemodel.ResponseModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository
@Inject constructor(val apiData: ApiData): IApiRepository{
    override fun getBusRoutes(): Observable<ResponseModel.BusRoute.ResponseSealed>{
        val observer = apiData.getBusRoutes().observeAndSubcribeOn()

        return observer.map { response ->
            Log.d("TAG", response.toString())
            if(response.isSuccessful){
                response.body()?.let {
                    ResponseModel.BusRoute.ResponseSealed.Success(
                    it
                ) }
            }else{
                try {
                    ResponseModel.BusRoute.ResponseSealed.Fail(response)
                }catch (e: Exception){
                    ResponseModel.BusRoute.ResponseSealed.Fail(response)
                }
            }
        }
    }

    override fun getBusStops(): Observable<ResponseModel.BusStop.ResponseSealed> {
        val observable = apiData.getBusStops().observeAndSubcribeOn()

        return observable.map { response ->
            Log.d("TAG", response.toString())

            if(response.isSuccessful){
                response.body()?.let {
                    ResponseModel.BusStop.ResponseSealed.Success(
                        it
                    )
                }
            }else{
                try {
                    ResponseModel.BusStop.ResponseSealed.Fail(response)
                }catch (e: Exception){
                    ResponseModel.BusStop.ResponseSealed.Fail(response)
                }
            }

        }
    }

    override fun getBusByBusRouteID(busRouteId: String): Observable<ResponseModel.BusData.ResponseSealed> {
        val observable = apiData.getBusByBusRouteId(busRouteId).observeAndSubcribeOn()

        return observable.map { response ->
            if(response.isSuccessful){
                response.body()?.let{
                    ResponseModel.BusData.ResponseSealed.Success(
                        it
                    )
                }
            }else{
                try {
                    ResponseModel.BusData.ResponseSealed.Fail(response)
                }catch (e: java.lang.Exception){
                    ResponseModel.BusData.ResponseSealed.Fail(response)
                }
            }
        }
    }
}