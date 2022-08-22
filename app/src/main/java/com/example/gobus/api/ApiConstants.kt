package com.example.gobus.api

class ApiConstants {
    companion object{
        const val BUS_ROUTES = "get-routes"
        const val BUS_STOPS = "get-busStops"
        const val GET_BUS_BY_BUS_ROUTE_ID = "get-buses/{busRouteId}"
        const val GET_ROUTE_LINE = "v2-get-route-lines"
    }
}