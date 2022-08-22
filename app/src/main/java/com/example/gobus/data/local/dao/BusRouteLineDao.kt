package com.example.gobus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gobus.data.local.entity.BusRouteLineEntity
import io.reactivex.Single


@Dao
interface BusRouteLineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(busRouteLineEntity: BusRouteLineEntity): Single<Long>

    @Query("SELECT * FROM bus_route_line WHERE bus_route_id = :busRouteId")
    fun getBusRouteLineByID(busRouteId: Long): BusRouteLineEntity
}