package com.example.gobus.responsemodel

import retrofit2.Response
import java.io.Serializable
import java.util.*

class ResponseModel{
    class BusRoute{
        sealed class ResponseSealed{
            data class Success(val result: List<Result>?): ResponseSealed()
            data class Fail(val response: Response<List<Result>>): ResponseSealed()
        }
        data class Result(
            var BusRouteId: Long?,
            var Origine: Long?,
            var Destination: Long?,
            var RouteNo: String?,
            var Fare: Int?,
            var TripsPerDay: Int?,
            var Frequency: Int?,
            var RouteShortName: String?,
            var Distance: Double?,
            var RouteName: String?,
            var FirstDepartureTime: Date?,
            var LastDepartureTime: Date?,
            var RouteInBound: String?,
            var RouteOutBound: String?,
            var color: String?,
        ): Serializable
    }
    class BusStop{
        sealed class ResponseSealed{
            data class Success(val result: List<Result>?): ResponseSealed()
            data class Fail(val responseBody: Response<List<Result>>): ResponseSealed()
        }
        data class Result(
            var BusStopId: Long?,
            var Name: String?,
            var PlaceNameFromName: String?,
            var Lat: String?,
            var Lon: String?
        ) : Serializable
    }
    class BusData{
        sealed class ResponseSealed{
            data class Success(val result: List<Result>?): ResponseSealed()
            data class Fail(val responseBody: Response<List<Result>>): ResponseSealed()
        }
        data class Result(
            var VehicleId: Long?,
        ): Serializable
    }
    class BusRouteLine{
        sealed class ResponseSealed{
            data class Success(val result: List<Result>): ResponseSealed()
            data class Fail(val responseBody: Response<List<Result>>): ResponseSealed()
        }
        data class Result(
            var BusRouteId: Int?,
            var Color: String?,
            var OutBoundPoints:List<BoundPoints?>,
            var InBoundPoints: List<BoundPoints?>
        ): Serializable
        data class BoundPoints(
            var StartBusStopId: Int?,
            var EndBusStopId: Int?,
            var Points: List<Points>
        ): Serializable
        data class Points(
            var Latitude: Double?,
            var Longitude: Double?
        ): Serializable
    }
}
