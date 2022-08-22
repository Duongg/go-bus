package com.example.gobus.repository

import android.util.Log
import com.example.gobus.api.ApiData
import com.example.gobus.data.local.entity.BusRouteLineEntity
import com.example.gobus.data.local.dao.RoomData
import com.example.gobus.data.local.entity.BoundPointEntity
import com.example.gobus.data.local.entity.PointEntity
import com.example.gobus.extension.observeAndSubcribeOn
import com.example.gobus.responsemodel.ResponseModel
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository
@Inject constructor(
    private val roomData: RoomData,
    val apiData: ApiData
) : IApiRepository {
    override fun getBusRoutes(): Observable<ResponseModel.BusRoute.ResponseSealed> {
        val observer = apiData.getBusRoutes().observeAndSubcribeOn()

        return observer.map { response ->
            Log.d("TAG", response.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseModel.BusRoute.ResponseSealed.Success(
                        it
                    )
                }
            } else {
                try {
                    ResponseModel.BusRoute.ResponseSealed.Fail(response)
                } catch (e: Exception) {
                    ResponseModel.BusRoute.ResponseSealed.Fail(response)
                }
            }
        }
    }

    override fun getBusStops(): Observable<ResponseModel.BusStop.ResponseSealed> {
        val observable = apiData.getBusStops().observeAndSubcribeOn()

        return observable.map { response ->
            Log.d("TAG", response.toString())

            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseModel.BusStop.ResponseSealed.Success(
                        it
                    )
                }
            } else {
                try {
                    ResponseModel.BusStop.ResponseSealed.Fail(response)
                } catch (e: Exception) {
                    ResponseModel.BusStop.ResponseSealed.Fail(response)
                }
            }

        }
    }

    override fun getBusByBusRouteID(busRouteId: String): Observable<ResponseModel.BusData.ResponseSealed> {
        val observable = apiData.getBusByBusRouteId(busRouteId).observeAndSubcribeOn()

        return observable.map { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseModel.BusData.ResponseSealed.Success(
                        it
                    )
                }
            } else {
                try {
                    ResponseModel.BusData.ResponseSealed.Fail(response)
                } catch (e: java.lang.Exception) {
                    ResponseModel.BusData.ResponseSealed.Fail(response)
                }
            }
        }
    }

    override fun getBusRouteLine(): Observable<ResponseModel.BusRouteLine.ResponseSealed> {
        val observable = apiData.getBusRouteLine().observeAndSubcribeOn()

        return observable.map { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseModel.BusRouteLine.ResponseSealed.Success(
                        it
                    )
                }
            } else {
                try {
                    ResponseModel.BusRouteLine.ResponseSealed.Fail(response)
                } catch (e: java.lang.Exception) {
                    ResponseModel.BusRouteLine.ResponseSealed.Fail(response)
                }
            }
        }
    }

    override fun insertBusRouteLines(busRouteLine: ResponseModel.BusRouteLine.Result) {
        var outBoundPointID = 0
        var inBoundPointID = 0
        var pointId = 0
        val points: MutableList<PointEntity> = mutableListOf()
        val outBoundPointList: MutableList<BoundPointEntity> = mutableListOf()
        val inBoundPointList: MutableList<BoundPointEntity> = mutableListOf()
        for (item in busRouteLine.OutBoundPoints) {
            if (item != null) {
                for (p in item.Points) {
                    pointId += 1
                    val point = PointEntity(pointId, p.Latitude, p.Longitude)
                    points.add(point)
                }
                outBoundPointID += 1
                val outBoundPoint =
                    BoundPointEntity(
                        outBoundPointID,
                        item?.StartBusStopId,
                        item?.EndBusStopId,
                        points
                    )
                outBoundPointList.add(outBoundPoint)
            }
        }
        for (item in busRouteLine.InBoundPoints) {
            if (item != null) {
                for (p in item.Points) {
                    pointId += 1
                    val point = PointEntity(pointId, p.Latitude, p.Longitude)

                    points?.add(point)
                }
                inBoundPointID += 1
                val inBoundPoint =
                    BoundPointEntity(
                        inBoundPointID,
                        item?.StartBusStopId,
                        item?.EndBusStopId,
                        points
                    )
                inBoundPointList?.add(inBoundPoint)
            }
        }


        val busRoute = BusRouteLineEntity(
            BusRouteId = busRouteLine.BusRouteId!!,
            Color = busRouteLine.Color.toString(),
            OutBoundPoints = outBoundPointList!!,
            InBoundPoints = inBoundPointList!!
        )

        val s2 = roomData.busRouteLineDao().insertAll(busRoute)
        val s3 = s2.`as`(RxJavaBridge.toV3Single<Long>())
        s3.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1: Long?, t2: Throwable? ->
            }

    }

}