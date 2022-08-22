package com.example.gobus.data.local.entity

import androidx.room.*


@Entity(tableName = "bound_point")
data class BoundPointEntity(
    @PrimaryKey(autoGenerate = true)
    val BoundPointId: Int? = 0,

    @ColumnInfo(name = "start_bus_stop_id")
    var StartBusStopId: Int?,

    @ColumnInfo(name = "end_bus_stop_id")
    var EndBusStopId: Int?,

    @ColumnInfo(name = "point")
    var Points: List<PointEntity>?
)
