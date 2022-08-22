package com.example.gobus.requestmodel

class RequestModel {
    class RoutesFindingModel {
        data class Origin(
            var Latitude: String,
            var Longitude: String
        )
        data class Destination(
            var Latitude: String,
            var Longitude: String
        )
    }

}

