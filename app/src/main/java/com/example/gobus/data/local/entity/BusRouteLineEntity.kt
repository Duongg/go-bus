package com.example.gobus.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bus_route_line")
data class BusRouteLineEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "bus_route_id")
    var BusRouteId: Int,

    @ColumnInfo(name = "color")
    var Color: String,

    @ColumnInfo(name="out_bound_point")
    var OutBoundPoints: List<BoundPointEntity>,

    @ColumnInfo(name="in_bound_point")
    var InBoundPoints: List<BoundPointEntity>

)

