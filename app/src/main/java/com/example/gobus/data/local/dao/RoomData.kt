package com.example.gobus.data.local.dao

import android.content.Context
import android.graphics.Point
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gobus.data.local.entity.BoundPointEntity
import com.example.gobus.data.local.entity.BusRouteLineEntity
import com.example.gobus.data.local.entity.PointEntity
import com.example.gobus.utils.GsonConverter

@TypeConverters(value = [GsonConverter::class])
@Database(entities = [BusRouteLineEntity::class, BoundPointEntity::class, PointEntity::class], version = 1)
abstract class RoomData: RoomDatabase() {
    abstract fun busRouteLineDao(): BusRouteLineDao


    companion object{
        fun buildDatabase(context: Context): RoomData{
            return Room.databaseBuilder(
                context.applicationContext,
                RoomData::class.java, "bus_route_line.db"
            ).build()
        }
    }
}