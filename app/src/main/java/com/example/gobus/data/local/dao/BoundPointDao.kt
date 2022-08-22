package com.example.gobus.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gobus.data.local.entity.BoundPointEntity

interface BoundPointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(boundPointEntity: BoundPointEntity)

    @Query("SELECT * FROM bound_point WHERE bound_point_id = :boundPointId")
    fun getBusRouteLineByID(boundPointId: Long): BoundPointEntity
}