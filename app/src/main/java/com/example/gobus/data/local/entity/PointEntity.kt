package com.example.gobus.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "point")
data class PointEntity(
    @PrimaryKey(autoGenerate = true)
    val PointId: Int? = 0,

    @ColumnInfo(name = "latitude")
    val Latitude: Double?,

    @ColumnInfo(name = "longitude")
    val Longitude: Double?

)
